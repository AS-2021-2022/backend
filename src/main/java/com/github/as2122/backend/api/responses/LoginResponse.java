package com.github.as2122.backend.api.responses;

public class LoginResponse extends Response {

    final String token;

    public LoginResponse(String status, String token) {
        super(status);
        this.token = token;
    }
}
