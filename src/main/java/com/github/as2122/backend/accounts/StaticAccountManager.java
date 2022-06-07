package com.github.as2122.backend.accounts;

import org.springframework.stereotype.Component;

import java.util.*;

public class StaticAccountManager implements AccountManagerInterface {

    private final Map<String, Account> accounts = new HashMap<>();

    private final Map<String, String> allowedTokens = new HashMap<>();

    public StaticAccountManager() {
        accounts.put("user1", new Account("user1", "user1@nsn.pt", "password1", AccountLevel.EMPLOYEE));
        accounts.put("user2", new Account("user2", "user2@nsn.pt", "password2", AccountLevel.EMPLOYEE));
        accounts.put("user3", new Account("user3", "user3@nsn.pt", "password3", AccountLevel.MANAGER));
        accounts.put("user4", new Account("user4", "user4@nsn.pt", "password4", AccountLevel.ADMIN));
        accounts.put("user5", new Account("user5", "user5@nsn.pt", "password5", AccountLevel.EMPLOYEE));
        accounts.put("user6", new Account("user6", "user6@nsn.pt", "password6", AccountLevel.EMPLOYEE));
        accounts.put("user7", new Account("user7", "user7@nsn.pt", "password7", AccountLevel.EMPLOYEE));
        accounts.put("user8", new Account("user8", "user8@nsn.pt", "password8", AccountLevel.MANAGER));
    }

    @Override
    public String login(String username, String password) {
        if (!accounts.containsKey(username) || !accounts.get(username).getPassword().equals(password))
            return null;

        if (allowedTokens.containsKey(username))
            return allowedTokens.get(username);

        final String token = UUID.randomUUID().toString();
        allowedTokens.put(username, token);

        return token;
    }

    @Override
    public Account getByName(String name) {
        return accounts.get(name);
    }

    @Override
    public String getByToken(String token) {
        return allowedTokens.keySet().stream()
                .filter(name -> allowedTokens.get(name).equals(token))
                .findFirst().get();
    }
}
