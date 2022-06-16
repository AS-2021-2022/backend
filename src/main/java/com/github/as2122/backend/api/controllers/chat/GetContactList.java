package com.github.as2122.backend.api.controllers.chat;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.responses.ContactListResponse;
import com.github.as2122.backend.chat.ChatManager;
import com.github.as2122.backend.chat.Contact;
import com.github.as2122.backend.chat.Group;
import com.github.as2122.backend.teams.TeamManager;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetContactList {

    private final Gson jsonParser;
    private final AccountManagerInterface accountManager;
    private final TeamManager teamManager;
    private final ChatManager chatManager;

    public GetContactList(AccountManagerInterface accountManager, ChatManager chatManager, TeamManager teamManager, Gson jsonParser) {
        this.accountManager = accountManager;
        this.chatManager = chatManager;
        this.teamManager = teamManager;
        this.jsonParser = jsonParser;
    }

    @GetMapping("/contacts")
    public String getContactList(String token) {
        final String user = accountManager.getByToken(token);
        final List<Group> groups = chatManager.getGroupsForUser(user);
        final List<Contact> contacts = teamManager.getContacts(user);
        return jsonParser.toJson(new ContactListResponse("accepted", groups, contacts));
    }
}
