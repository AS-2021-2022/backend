package com.github.as2122.backend.api.controllers.tasks;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.controllers.task.AssignTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(AssignTask.class)
class AssignTaskTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountManagerInterface accountManager;

    @Test
    public void testAssignTaskSuccessfully() throws Exception {
        final String user = accountManager.login("user3", "password3");

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/assignTask?token=" + user + "&name=fortynayt&start=16/06/2022&end=30/06/2022&priority=default&assignee_id=user1@nsn.pt&description=babagee")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("accepted"))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testAssignTaskUnsuccessfully() throws Exception {
        final String user = accountManager.login("user1", "password1");

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/assignTask?token=" + user + "&name=lucitik&start=16/06/2022&end=30/06/2022&priority=default&assignee_id=user3@nsn.pt")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("rejected"))
                .andDo(print())
                .andReturn();
    }
}
