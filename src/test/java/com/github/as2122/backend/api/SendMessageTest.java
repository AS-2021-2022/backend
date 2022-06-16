package com.github.as2122.backend.api;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.controllers.chat.SendMessage;
import com.github.as2122.backend.chat.ChatManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(SendMessage.class)
class SendMessageTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountManagerInterface accountManager;

    @Autowired
    private ChatManager chatManager;

    @Test
    public void sendMessageWhenLoggedIn() throws Exception {
        final String sender = accountManager.login("user1", "password1");
        final String receiver = accountManager.login("user2", "password2");

        final String target = accountManager.getByName(accountManager.getByToken(receiver)).getId();

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/sendMessage?token=" + sender + "&targetID=" + target + "&message=someMessage")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("accepted"))
                .andReturn();

        chatManager.getMessages(
                accountManager.getByName(accountManager.getByToken(sender)).getId(), target, 0, 100)
                .forEach(message -> System.out.println(message.getOrigin() + ": " + message.getText()));
    }
}