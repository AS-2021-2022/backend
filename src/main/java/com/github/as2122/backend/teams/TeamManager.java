package com.github.as2122.backend.teams;

import com.github.as2122.backend.accounts.Account;
import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.chat.Contact;
import com.github.as2122.backend.chat.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@Component
public class TeamManager {
    private final List<Team> teamList = new ArrayList<>();

    public TeamManager(@Autowired AccountManagerInterface accountManager) {
        final List<Account> t1 = new ArrayList<>();
        t1.add(accountManager.getByName("user1"));
        t1.add(accountManager.getByName("user2"));

        final List<Account> t2 = new ArrayList<>();
        t1.add(accountManager.getByName("user5"));
        t1.add(accountManager.getByName("user6"));
        t1.add(accountManager.getByName("user7"));

        this.teamList.add(new Team(accountManager.getByName("user3"), t1));
        this.teamList.add(new Team(accountManager.getByName("user8"), t2));
    }

    public List<Contact> getContacts(String username) {
        final Set<Contact> res = new HashSet<>();

        for (Team team : teamList) {
            final List<Contact> teamContacts = team.getEmployees().stream()
                    .map(account -> new Contact(account.getName(), account.getId(), Status.ONLINE))
                    .collect(Collectors.toList());
            final Account manager = team.getManager();
            teamContacts.add(new Contact(manager.getName(), manager.getId(), Status.ONLINE));

            if (teamContacts.stream().anyMatch(contact -> contact.getName().equals(username)))
                res.addAll(teamContacts);
        }

        return new ArrayList<>(res);
    }
}
