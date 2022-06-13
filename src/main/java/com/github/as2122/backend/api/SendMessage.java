package com.github.as2122.backend.api;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.responses.Response;
import com.github.as2122.backend.api.responses.SendMessageResponse;
import com.github.as2122.backend.chat.ChatManager;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessage {

    private final ChatManager chatManager;
    private final AccountManagerInterface accountManager;
    private final Gson jsonParser;

    public SendMessage(ChatManager chatManager, AccountManagerInterface accountManager, Gson jsonParser) {
        this.chatManager = chatManager;
        this.accountManager = accountManager;
        this.jsonParser = jsonParser;
    }

    @GetMapping("/sendMessage")
    public String getMessage(String token, String targetID, String message) {
        chatManager.sendMessage(
//                targetID.contains("@") ?
//                        accountManager.getByName(accountManager.getByToken(token)).getId() :
//                        token,
                accountManager.getByName(accountManager.getByToken(token)).getId(),
                targetID, message);

        return jsonParser.toJson(new SendMessageResponse("accepted"));
    }
}
