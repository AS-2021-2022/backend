package com.github.as2122.backend.api.requests;

public class LoginRegister extends RequestHeader {
    private final String username;
    private final String password;

    public LoginRegister(String token, Request query, String username, String password) {
        super(token, query);
        this.username = username;
        this.password = password;
    }
}
