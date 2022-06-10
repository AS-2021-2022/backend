package com.github.as2122.backend.api.responses;

public class LoginResponse extends Response {

    final String token;
    final String email;

    public LoginResponse(String status, String token, String email) {
        super(status);
        this.token = token;
        this.email = email;
    }

    public LoginResponse(String status, String token) {
        this(status, token, null);
    }
}
