package com.github.as2122.backend.accounts;

public interface AccountManagerInterface {
    String login(String username, String password);
    default AccountLevel getPermissions(String token) {return null;}

    Account getByName(String name);

    String getByToken(String token);
}
