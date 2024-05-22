package com.example.demo.controllers.responses;

import com.example.demo.models.User;

public class TokenVerificationResponse {
    private User user;
    private String accessToken;

    public TokenVerificationResponse() {}

    public TokenVerificationResponse(User user, String accessToken) {
        this.user = user;
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}