package com.github.as2122.backend.accounts;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StaticAccountManager implements AccountManagerInterface {

    private final Map<String, String> allowedTokens = new HashMap<>();

    private final Map<String, String> userPasswords = new HashMap<>();

    public StaticAccountManager() {
        userPasswords.put("user1", "password");
    }

    @Override
    public String getToken(String username, String password) {
        if (allowedTokens.containsKey(username))
            return allowedTokens.get(username);

        final String token = String.valueOf(allowedTokens.size());
        allowedTokens.put(username, token);

        return token;
    }
}
