package com.github.as2122.backend.api.controllers.tasks;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.controllers.task.ConcludeTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ConcludeTask.class)
class ConcludeTaskTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountManagerInterface accountManager;

    @Test
    public void testConcludeTask() throws Exception {
        final String user = accountManager.login("user1", "password1");

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/concludeTask?token=" + user + "&id=0")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("accepted"))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testConcludeNonExistentTask() throws Exception {
        final String user = accountManager.login("user1", "password1");

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/concludeTask?token=" + user + "&id=23")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("rejected"))
                .andDo(print())
                .andReturn();
    }
}