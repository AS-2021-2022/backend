package com.github.as2122.backend.teams;

import com.github.as2122.backend.accounts.Account;

import java.util.List;

public class Team {
    private final Account manager;
    private final List<Account> employees;


    public Team(Account manager, List<Account> employees) {
        this.manager = manager;
        this.employees = employees;
    }

    public Account getManager() {
        return manager;
    }

    public List<Account> getEmployees() {
        return employees;
    }
}
