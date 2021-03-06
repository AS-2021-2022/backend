package com.github.as2122.backend.api.controllers.workflows;

import com.github.as2122.backend.accounts.AccountManagerInterface;
import com.github.as2122.backend.api.controllers.workflows.GetWorkflows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(GetWorkflows.class)
class GetWorkflowsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountManagerInterface accountManager;

    @Test
    public void getWorkflows() throws Exception {
        final String user = accountManager.login("user1", "password1");

        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getWorkflows?token=" + user)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("accepted"))
                .andReturn();
    }
}