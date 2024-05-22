package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.ProcessorException;
import com.example.demo.controllers.requests.ChangePasswordRequest;
import com.example.demo.models.User;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/change-password")
    public User changePassword(@RequestBody ChangePasswordRequest request) throws ProcessorException {
        return userService.changePassword(request.getUserId(), request.getOldPassword(), request.getNewPassword());
    }
}