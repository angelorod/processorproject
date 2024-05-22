package com.example.demo.controllers.requests;

public class ForgotPasswordRequest {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String email) {
        this.userName = email;
    }
}