package com.github.as2122.backend.api;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final AccountManagerInterface accountManager;

    public LoginController(AccountManagerInterface accountManager) {
        this.accountManager = accountManager;
    }

    @GetMapping("/login")
    public String login(String request) {
        return accountManager.getToken(request, "pass");
    }
}
