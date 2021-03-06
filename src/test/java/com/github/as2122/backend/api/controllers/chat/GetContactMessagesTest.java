package com.github.as2122.backend.api.controllers.chat;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.controllers.chat.GetContactMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(GetContactMessages.class)
class GetContactMessagesTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountManagerInterface accountManager;

    @Test
    public void testGroupMessages() throws Exception {
        final String sender = accountManager.login("user1", "password1");
//        final String receiver = accountManager.login("user2", "password2");

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/contactMessages?token=" + sender + "&targetID=" + "g10001" + "&depth=0&n=10")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("accepted"))
//                .andExpect(MockMvcResultMatchers.jsonPath("messages").value("[{\"origin\":\"user1\",\"text\":\"hello\"},{\"origin\":\"user2\",\"text\":\"hi\"},{\"origin\":\"user3\",\"text\":\"silence please don\\u0027t clog my email\"}]"))
                .andReturn();
    }

    @Test
    public void testDirectMessages() throws Exception {
        final String usr1 = accountManager.login("user1", "password1");
        final String usr2 = accountManager.login("user2", "password2");

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/contactMessages?token=" + usr1 + "&targetID=" + "user2@nsn.pt" + "&depth=0&n=10")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("accepted"))
//                .andExpect(MockMvcResultMatchers.jsonPath("messages").value("[{\"origin\":\"user1\",\"text\":\"hello\"},{\"origin\":\"user2\",\"text\":\"hi\"},{\"origin\":\"user3\",\"text\":\"silence please don\\u0027t clog my email\"}]"))
                .andExpect(MockMvcResultMatchers.jsonPath("messages").isNotEmpty())
                .andReturn();

        final RequestBuilder requestBuilder2 = MockMvcRequestBuilders
                .get("/contactMessages?token=" + usr2 + "&targetID=" + "user1@nsn.pt" + "&depth=0&n=10")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder2)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("accepted"))
//                .andExpect(MockMvcResultMatchers.jsonPath("messages").value("[{\"origin\":\"user1\",\"text\":\"hello\"},{\"origin\":\"user2\",\"text\":\"hi\"},{\"origin\":\"user3\",\"text\":\"silence please don\\u0027t clog my email\"}]"))
                .andExpect(MockMvcResultMatchers.jsonPath("messages").isNotEmpty())
                .andReturn();
    }
}