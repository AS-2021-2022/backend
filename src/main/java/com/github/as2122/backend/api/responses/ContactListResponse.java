package com.github.as2122.backend.api.responses;

import com.github.as2122.backend.chat.Contact;
import com.github.as2122.backend.chat.Group;

import java.util.List;

public class ContactListResponse extends Response {
    private final List<Group> groups;
    private final List<Contact> users;

    public ContactListResponse(String accepted, List<Group> groups, List<Contact> users) {
        super(accepted);
        this.groups = groups;
        this.users = users;
    }
}
