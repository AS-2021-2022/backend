package com.github.as2122.backend.accounts;

import java.util.*;

public class StaticAccountManager implements AccountManagerInterface {

    private final Map<String, Account> accounts = new HashMap<>();

    private final Map<String, String> allowedTokens = new HashMap<>();

    public StaticAccountManager() {
        accounts.put("user1", new Account("user1", "password1", AccountLevel.EMPLOYEE));
        accounts.put("user2", new Account("user2", "password2", AccountLevel.EMPLOYEE));
        accounts.put("user3", new Account("user3", "password3", AccountLevel.MANAGER));
        accounts.put("user4", new Account("user4", "password4", AccountLevel.ADMIN));
    }

    @Override
    public String login(String username, String password) {
        if (allowedTokens.containsKey(username))
            return allowedTokens.get(username);

        final String token = UUID.randomUUID().toString();
        allowedTokens.put(username, token);

        return token;
    }
}
