package com.github.as2122.backend.api;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(SendMessage.class)
class SendMessageTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountManagerInterface accountManager;

    @Test
    public void sendMessageWhenLoggedIn() throws Exception {
        final String sender = accountManager.login("user1", "password1");
        final String receiver = accountManager.login("user2", "password2");

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/sendMessage?token="+sender+"&targetID="+receiver+"&message=someMessage")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("accepted"))
                .andReturn();
    }
}