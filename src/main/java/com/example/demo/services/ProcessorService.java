package com.example.demo.services;

import com.example.demo.models.Processor;
import com.example.demo.models.User;
import com.example.demo.repositories.ProcessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProcessorService {

    private final ProcessorRepository processorRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ProcessorService(ProcessorRepository processorRepository, BCryptPasswordEncoder passwordEncoder) {
        this.processorRepository = processorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Processor saveProcessor(String userName, String password) {
        Processor processor = new Processor();
        processor.setName(userName);

        User user = new User();
        user.setUserName(userName);
        user.setPassword(passwordEncoder.encode(password));
        user.setProcessor(processor);

        processor.addUserToStaff(user);

        return processorRepository.save(processor);
    }

    public void deleteProcessor(Long id) {
        processorRepository.deleteById(id);
    }
}
