package com.github.as2122.backend.api;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.requests.Login;
import com.github.as2122.backend.api.requests.Request;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;

@RestController
public class LoginController {

    private final Gson jsonParser;
    private final AccountManagerInterface accountManager;

    public LoginController(@Autowired Gson jsonParser, @Autowired AccountManagerInterface accountManager) {
        this.jsonParser = jsonParser;
        this.accountManager = accountManager;
    }

    @GetMapping("/login")
    public String login(String request) {
        final Login loginRequest = (Login) jsonParser.fromJson(request, Request.class).getParams();
        if (loginRequest == null)
            return null;

        return accountManager.getToken(loginRequest.getUsername(), loginRequest.getPassword());
    }
}
