package com.github.as2122.backend.api.controllers;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.responses.LoginResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final Gson jsonParser;
    private final AccountManagerInterface accountManager;

    public LoginController(@Autowired Gson jsonParser, @Autowired AccountManagerInterface accountManager) {
        this.jsonParser = jsonParser;
        this.accountManager = accountManager;
    }

    @GetMapping("/login")
    public String login(String username, String password) {
        final String token = accountManager.login(username, password);
        return token == null ? jsonParser.toJson(new LoginResponse("rejected", null)) :
                jsonParser.toJson(new LoginResponse("accepted", token, accountManager.getByName(accountManager.getByToken(token)).getId()));
    }
}
