package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.ProcessorException;
import com.example.demo.controllers.requests.ForgotPasswordRequest;
import com.example.demo.controllers.requests.RegisterUserRequest;
import com.example.demo.controllers.requests.ResetPasswordRequest;
import com.example.demo.controllers.requests.dummy.DummyRequest;
import com.example.demo.models.User;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterUserRequest request) throws ProcessorException {
        return userService.registerUser(request.getUsername(), request.getEmail(), request.getPassword());
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody ForgotPasswordRequest request) throws ProcessorException {
        userService.forgotPassword(request.getUserName());
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody ResetPasswordRequest request) throws ProcessorException {
        userService.resetPassword(request.getUserName(), request.getVerificationCode(), request.getNewPassword());
    }

    @PostMapping("/login")
    public User login(@RequestBody DummyRequest request) {
        return userService.login(request.getUserName(), request.getPassword());
    }
}