package com.github.as2122.backend.api.controllers.chat;

import com.github.as2122.backend.accounts.Account;
import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.responses.GetContactMessagesResponse;
import com.github.as2122.backend.chat.ChatManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetContactMessages {

    private final Gson jsonSerializer;
    private final ChatManager chatManager;
    private final AccountManagerInterface accountManager;

    public GetContactMessages(ChatManager chatManager, AccountManagerInterface accountManager, Gson jsonSerializer) {
        this.chatManager = chatManager;
        this.accountManager = accountManager;
        this.jsonSerializer = jsonSerializer;
    }

    @GetMapping("/contactMessages")
    public String getContactMessages(String token, String targetID, int depth, int n) {
        return jsonSerializer.toJson(
                new GetContactMessagesResponse("accepted",
                        chatManager.getMessages(
                                accountManager.getByName(accountManager.getByToken(token)).getId(),
                                targetID, depth, n)
                )
        );
    }
}
