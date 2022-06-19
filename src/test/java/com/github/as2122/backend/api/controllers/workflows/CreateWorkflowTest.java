package com.github.as2122.backend.api.controllers.workflows;

import com.github.as2122.backend.api.controllers.workflows.CreateWorkflow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CreateWorkflow.class)
class CreateWorkflowTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createWorkflow() throws Exception {

        mockMvc.perform(post("/createWorkflow")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"xd\", \"steps\": [{\"id\":\"user1\",\"description\":\"bababowie\"}]}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("accepted"))
                .andReturn();
    }
}
