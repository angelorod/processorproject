package com.example.demo.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.ProcessorException;
import com.example.demo.controllers.responses.TokenVerificationResponse;
import com.example.demo.models.Processor;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.ProcessorRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.utils.JwtTokenUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
  
   private final UserRepository userRepository;
   private final ProcessorRepository processorRepository;
   private final PasswordEncoder passwordEncoder;
   private final JwtTokenUtil jwtTokenUtil;
  
   @Autowired
   public UserService(UserRepository userRepository, ProcessorRepository processorRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
       this.userRepository = userRepository;
       this.processorRepository = processorRepository;
       this.passwordEncoder = passwordEncoder;
       this.jwtTokenUtil = jwtTokenUtil;
   }

    //Done
    public User registerUser(String username, String email, String password) throws ProcessorException {
        // Check if a processor with this owner already exists
        if (processorRepository.findByOwner(username).isPresent()) {
            throw new ProcessorException("Processor with this owner already exists");
        }
        
        User user = new User(username, email, password);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        Processor processor = new Processor();
        processor.setName(username); // Set the processor name to the username
        processor.setOwner(username); // Set the processor owner to the username
        
        // Generate a unique public merchant ID
        String publicMerchantId;
        do {
            publicMerchantId = generateRandomString(8, true, true, true);
        } while (processorRepository.findByPublicMerchantId(publicMerchantId).isPresent());
        
        processor.setPublicMerchantId(publicMerchantId);
        
        processor.addUserToStaff(user); // Add user to staff first
        
        processorRepository.save(processor); // Save processor, which will also save the user
            
        return user;
    }

    private String generateRandomString(int length, boolean includeUppercase, boolean includeLowercase, boolean includeDigits) throws ProcessorException {
        String characters = "";
        if (includeUppercase) {
            characters += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
        if (includeLowercase) {
            characters += "abcdefghijklmnopqrstuvwxyz";
        }
        if (includeDigits) {
            characters += "0123456789";
        }
        
        if (characters.isEmpty()) {
            throw new ProcessorException("At least one character type should be included");
        }
        
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    //Done
    public void forgotPassword(String userName) throws ProcessorException {
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new ProcessorException("User not found"));
        
        if (user.getRole() == Role.LOGIN) {
            throw new ProcessorException("Unable to reset account password");
        }
        
        String verificationCode = generateRandomString(6, false, false, true);
        user.setVerificationCode(verificationCode);
        
        // Set the verification code expiration to 10 minutes in the future
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 10);
        user.setVerificationCodeExpiration(calendar.getTime());
        
        userRepository.save(user);
    }

    // Done
    public void resetPassword(String userName, String verificationCode, String newPassword) throws ProcessorException {
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new ProcessorException("User not found"));
        
        if (user.getRole() == Role.LOGIN) {
            throw new ProcessorException("Unable to reset account password");
        }
        
        if (!verificationCode.equals(user.getVerificationCode())) {
            throw new ProcessorException("Invalid verification code");
        }
        
        if (user.getVerificationCodeExpiration().before(new Date())) {
            throw new ProcessorException("Verification code has expired");
        }
        
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);
        user.setVerificationCode(null);
        user.setVerificationCodeExpiration(null);
        userRepository.save(user);
    }

   public User login(String userName, String password) {
        User user = userRepository.findByUserName(userName).orElseThrow();
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }

    // Done
    public User changePassword(Long userId, String oldPassword, String newPassword) throws ProcessorException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ProcessorException("User not found"));
        
        if (user.getRole() == Role.LOGIN) {
            throw new ProcessorException("Unable to change account password");
        }
        
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            String encodedNewPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encodedNewPassword);
            user.setVerificationCode(null);
            user.setVerificationCodeExpiration(null);
            return userRepository.save(user);
        } else {
            throw new ProcessorException("Old password is incorrect");
        }
    }

    // Done
    public void changeSharedPassword(Long userId, String newPassword) throws ProcessorException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ProcessorException("User not found"));
        
        Processor processor = user.getProcessor();
        
        if (processor == null) {
            throw new ProcessorException("User is not a staff member of any processor");
        }
        
        User loginStaff = processor.getStaff().stream()
                .filter(staff -> staff.getRole() == Role.LOGIN)
                .findFirst().orElseThrow(() -> new ProcessorException("Login staff not found for the processor"));
        
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        loginStaff.setPassword(encodedNewPassword);
        userRepository.save(loginStaff);
    }

    // Done
    public void resetPin(String processorId, String verificationCode, String phone, String pin) throws ProcessorException {
        Processor processor = processorRepository.findById(Long.parseLong(processorId)).orElseThrow(() -> new ProcessorException("Processor not found"));
        User user = processor.getStaff().stream().filter(usr -> usr.getUseSharedLogin() && usr.getPhone().equals(phone)).findFirst().orElseThrow(() -> new ProcessorException("Invalid or unknown phone number"));

        if (!verificationCode.equals(user.getVerificationCode())) {
            throw new ProcessorException("Invalid verification code");
        }
    
        if (user.getVerificationCodeExpiration().before(new Date())) {
            throw new ProcessorException("Verification code has expired");
        }
    
        user.setVerificationCode(null);
        user.setVerificationCodeExpiration(null);
        user.setPassword(pin);
        userRepository.save(user);
    }

    // Done
    public void forgotPin(Long processorId, String phoneNumber) throws ProcessorException {
        Processor processor = processorRepository.findById(processorId).orElseThrow(() -> new ProcessorException("Processor not found"));
        User user = processor.getStaff().stream().filter(usr -> usr.getUseSharedLogin() && usr.getPhone().equals(phoneNumber)).findFirst().orElseThrow(() -> new ProcessorException("Invalid or unknown phone number"));
        
        String verificationCode = generateRandomString(6, false, false, true);
        user.setVerificationCode(verificationCode);
        
        // Set the verification code expiration to 10 minutes in the future
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 10);
        user.setVerificationCodeExpiration(calendar.getTime());
        
        userRepository.save(user);
    }

    // Done
    public TokenVerificationResponse verifyPin(long processorId, String pin) throws ProcessorException{
        Processor processor = processorRepository.findById(processorId).orElseThrow(() -> new ProcessorException("Processor not found"));
        User user = processor.getStaff().stream().filter(staff -> staff.getUseSharedLogin() && staff.getUserName().equals(pin)).findFirst().orElseThrow(() -> new ProcessorException("User not found."));
        
        String jwtToken = jwtTokenUtil.generateToken(user.getUserName(), user.getPhone());
        return new TokenVerificationResponse(user, jwtToken);
    }

    // Done
    @Transactional
    public void createSharedAccount(long processorId, String username, String password) throws ProcessorException {
        Processor processor = processorRepository.findById(processorId).orElseThrow(() -> new ProcessorException("Processor not found"));
    
        if (processor.getStaff().stream().anyMatch(staff -> staff.getRole() == Role.LOGIN)) {
            throw new ProcessorException("Processor already has a shared account");
        }
    
        boolean usernameAlreadyExists = processor.getStaff().stream()
            .anyMatch(staff -> username.equals(staff.getUserName()));
        if (usernameAlreadyExists) {
            throw new ProcessorException("Username already exists for another staff member");
        }
    
        User user = new User(username, "", password);
        user.setRole(Role.LOGIN);
    
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    
        processor.addUserToStaff(user);
        userRepository.save(user); // Explicitly save the new user
    }

    @Transactional
    public User createSharedLoginStaffMember(long processorId, String email, String phone, String name) throws ProcessorException {
        Processor processor = processorRepository.findById(processorId).orElseThrow(() -> new ProcessorException("Processor not found"));
        
        if (phone == null || phone.isEmpty()) {
            throw new ProcessorException("Phone number cannot be empty for staff members using shared login");
        }
        
        boolean phoneAlreadyExists = processor.getStaff().stream()
                .anyMatch(staff -> staff.getUseSharedLogin() && phone.equals(staff.getPhone()));
        if (phoneAlreadyExists) {
            throw new ProcessorException("Phone number already exists for another staff member using shared login");
        }
        
        AtomicReference<String> generatedUsername = new AtomicReference<>();
        do {
            generatedUsername.set(generateRandomString(4, false, false, true));
        } while (processor.getStaff().stream().anyMatch(staff -> generatedUsername.get().equals(staff.getUserName())));
    
        User user = new User(generatedUsername.get(), email, null); // Use the generated unique username
        
        user.setUseSharedLogin(true);
        user.setRole(Role.PROCESSOR);
        user.setPhone(phone);
        user.setName(name);
        
        processor.addUserToStaff(user);
        userRepository.save(user); // Save the new staff member
        
        return user;
    }
    @Transactional
    public User createStaffMember(long processorId, Role role, String username, String email, String phone, String name) throws ProcessorException {
        if (username == null || username.isEmpty()) {
            throw new ProcessorException("Username cannot be empty");
        }
    
        Processor processor = processorRepository.findById(processorId).orElseThrow(() -> new ProcessorException("Processor not found"));
    
        User user;
    
        boolean usernameAlreadyExists = processor.getStaff().stream()
            .anyMatch(staff -> !staff.getUseSharedLogin() && username.equals(staff.getUserName()));
        if (usernameAlreadyExists) {
            throw new ProcessorException("Username already exists for another staff member");
        }

        user = new User(username, email, "");
        String password = generateRandomString(8, true, true, true); // Generate a random password for the staff member
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
    
        user.setUseSharedLogin(false);
        user.setRole(role);
        user.setPhone(phone);
        user.setName(name);
    
        processor.addUserToStaff(user);
        userRepository.save(user); // Save the new staff member
    
        return user;
    }}

    