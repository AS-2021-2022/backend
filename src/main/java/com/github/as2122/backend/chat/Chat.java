package com.github.as2122.backend.chat;

import com.github.as2122.backend.accounts.Account;

import java.util.List;

public class Chat {
    private final List<Account> accountList;

    public Chat(List<Account> accountList) {
        this.accountList = accountList;
    }
}
