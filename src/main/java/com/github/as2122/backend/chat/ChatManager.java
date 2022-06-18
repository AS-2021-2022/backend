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
        idMessages.get("g10001").add(new Message("user1@nsn.pt", "hello"));
        idMessages.get("g10001").add(new Message("user2@nsn.pt", "hi"));
        idMessages.get("g10001").add(new Message("user3@nsn.pt", "silence please don't clog my email"));

        idMessages.put("user1@nsn.ptuser2@nsn.pt", new ArrayList<>());
        idMessages.get("user1@nsn.ptuser2@nsn.pt").add(new Message("user1@nsn.pt", "hello"));
        idMessages.get("user1@nsn.ptuser2@nsn.pt").add(new Message("user2@nsn.pt", "go to work"));
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
        if (!destId.contains("@")) {
            if (!idMessages.containsKey(destId)) {
                idMessages.put(destId, new ArrayList<>());
            }
        } else {
            if (idMessages.containsKey(sender + destId)) {
                destId = sender + destId;
            } else if (idMessages.containsKey(destId + sender)) {
                destId = destId + sender;
            } else {
                destId = sender + destId;
                idMessages.put(destId, new ArrayList<>());
            }
        }

        System.out.println("DESTID: " + destId);

        idMessages.get(destId).add(new Message(sender, message));
    }

    public List<Message> getMessages(String sender, String chatID, int start, int n) {


        if (chatID.contains("@")) {
            if (idMessages.containsKey(chatID + sender)) {
                chatID = chatID + sender;
            } else if (idMessages.containsKey(sender + chatID)) {
                chatID = sender + chatID;
            }
        }
//        else {
//            return new ArrayList<>();
//        }

        System.out.println("FinalChatId: " + chatID);
        return idMessages.containsKey(chatID) ?
                idMessages.get(chatID).stream().skip(start).limit(n).collect(Collectors.toList()):
                new ArrayList<>();
    }
}
