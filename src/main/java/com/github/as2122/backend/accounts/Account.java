package com.github.as2122.backend.accounts;

import java.util.Objects;

public class Account {
    private final String name;
    private final String password;
    private final AccountLevel level;

    public Account(String name, String password, AccountLevel level) {
        this.name = name;
        this.password = password;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public AccountLevel getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || (getClass() != o.getClass() && o.getClass() != String.class)) return false;

        if (o instanceof Account) {
            final Account account = (Account) o;
            return name.equals(account.name);
        } else {
            return name.equals(o);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
