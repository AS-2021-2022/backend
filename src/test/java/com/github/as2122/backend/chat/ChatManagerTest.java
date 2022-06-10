package com.github.as2122.backend.chat;

import com.github.as2122.backend.BackendApplication;
import com.github.as2122.backend.accounts.Account;
import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.accounts.StaticAccountManager;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringJUnit4ClassRunner.class)
class ChatManagerTest {
    private final AccountManagerInterface accountManager = new StaticAccountManager();
    private final ChatManager chatManager = new ChatManager(accountManager);

    @Test
    public void testSendDM() {
        final String token = accountManager.login("user1", "password1");
        final String sender = accountManager.getByName(accountManager.getByToken(token)).getId();

        List<Message> messages = chatManager.getMessages(sender, "user2@nsn.pt", 0, 100);
        messages.forEach(msg-> System.out.println(msg.getText()));

        assert messages.size() == 2;

        final String message = "final message";
        chatManager.sendMessage(sender, "user2@nsn.pt", message);

        messages = chatManager.getMessages(sender, "user2@nsn.pt", 0, 100);
        messages.forEach(msg-> System.out.println(msg.getText()));

        assert messages.size() == 3;
        assert messages.get(messages.size() - 1).getText().equals(message);
    }

    @Test
    public void testSendMessageToGroup() {
        final String token = accountManager.login("user1", "password1");

        List<Message> messages = chatManager.getMessages(token, "g10001", 0, 100);
        messages.forEach(msg-> System.out.println(msg.getText()));

        assert messages.size() == 3;

        final String message = "final message";
        chatManager.sendMessage(token, "g10001", message);

        messages = chatManager.getMessages(token, "g10001", 0, 100);
        messages.forEach(msg-> System.out.println(msg.getText()));

        assert messages.size() == 4;
        assert messages.get(messages.size() - 1).getText().equals(message);
    }
}