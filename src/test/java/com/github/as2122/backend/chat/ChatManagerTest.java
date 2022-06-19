package com.github.as2122.backend.chat;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.accounts.StaticAccountManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
class ChatManagerTest {
    private final AccountManagerInterface accountManager = new StaticAccountManager();
    private final ChatManager chatManager = new ChatManager(accountManager);

    @Test
    public void testSendDM() {
        final String token = accountManager.login("user1", "password1");
        final String sender = accountManager.getByName(accountManager.getByToken(token)).getId();
        final String tk2 = accountManager.login("user2", "password2");
        final String sender2 = accountManager.getByName(accountManager.getByToken(tk2)).getId();

        List<Message> messages = chatManager.getMessages(sender, "user2@nsn.pt", 0, 100);
        messages.forEach(msg-> System.out.println(msg.getText()));

        assert messages.size() == 2;

        final String message = "final message";
        chatManager.sendMessage(sender, "user2@nsn.pt", message);

        messages = chatManager.getMessages(sender, "user2@nsn.pt", 0, 100);
//        messages.forEach(msg-> System.out.println(msg.getText()));
//        System.out.println("...........");

        assert messages.size() == 3;
        assert messages.get(messages.size() - 1).getText().equals(message);

        final String message2 = "final final message";
        chatManager.sendMessage(sender2, "user1@nsn.pt", message2);

        messages = chatManager.getMessages(sender2, "user1@nsn.pt", 0, 100);
//        messages.forEach(msg-> System.out.println(msg.getText()));
//        System.out.println("...........");

        assert messages.size() == 4;
        assert messages.get(messages.size() - 1).getText().equals(message2);
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

    @Test
    public void getGroupsForUserTest() {
        final List<Group> u1Grpugs = chatManager.getGroupsForUser("user1");
        Assertions.assertEquals(u1Grpugs.size(), 1);
        Assertions.assertEquals(u1Grpugs.get(0).getName(), "g1");

        final List<Group> u2Grpugs = chatManager.getGroupsForUser("user2");
        Assertions.assertEquals(u2Grpugs.size(), 2);
        Assertions.assertEquals(u2Grpugs.get(0).getName(), "g1");
        Assertions.assertEquals(u2Grpugs.get(1).getName(), "g2");
    }
}