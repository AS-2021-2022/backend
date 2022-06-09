package com.github.as2122.backend.chat;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Component
public class ChatManager {
//    private final TeamManager teamManager;

    //    @Autowired
//    private AccountManagerInterface accountManager;
    private final Map<Group, List<Contact>> groups = new HashMap<>();
    private final Map<String, List<Message>> idMessages = new HashMap<>();

    public ChatManager(AccountManagerInterface accountManager) {
//        this.accountManager = accountManager;

        final List<Contact> g1 = new ArrayList<>();
        g1.add(accountManager.getByName("user1").toContact());
        g1.add(accountManager.getByName("user2").toContact());
        g1.add(accountManager.getByName("user3").toContact());

        final List<Contact> g2 = new ArrayList<>();
        g1.add(accountManager.getByName("user5").toContact());
        g1.add(accountManager.getByName("user6").toContact());

        final List<Contact> g3 = new ArrayList<>();
        g1.add(accountManager.getByName("user7").toContact());
        g1.add(accountManager.getByName("user8").toContact());

        groups.put(new Group("g1", "g10001"), g1);
        groups.put(new Group("g2", "g20002"), g2);
        groups.put(new Group("g3", "g30003"), g3);

        idMessages.put("g10001", new ArrayList<>());
        idMessages.get("g10001").add(new Message("user1", "hello"));
        idMessages.get("g10001").add(new Message("user2", "hi"));
        idMessages.get("g10001").add(new Message("user3", "silence please don't clog my email"));
    }

    public List<Group> getGroupsForUser(String username) {

        final List<Group> groups = new ArrayList<>();
        this.groups.forEach((group, contacts) -> {
            if (contacts.stream().anyMatch(account -> account.getName().equals(username)))
                groups.add(group);
        });
        return groups;
    }

    public void sendMessage(String sender, String destId, String message) {
        if (!idMessages.containsKey(destId))
            idMessages.put(destId, new ArrayList<>());

        idMessages.get(destId).add(new Message(sender, message));
    }

    public List<Message> getMessages(String chatID, int start, int n) {
        if (!idMessages.containsKey(chatID)) {
            System.out.println("NO CONTAINS! " + chatID);
            idMessages.keySet().forEach(System.out::println);
            return new ArrayList<>();
        }

        return idMessages.get(chatID).stream().skip(start).limit(n).collect(Collectors.toList());
    }
}
