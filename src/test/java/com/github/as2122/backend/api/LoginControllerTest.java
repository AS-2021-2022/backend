package com.github.as2122.backend.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

//@RunWith(SpringRunner.class)
//@WithMockUser
@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLoginCorrect() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/login?username=user1&password=password")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.jsonPath("token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("token").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("accepted"))
                .andReturn();
    }

    @Test
    void testLoginIncorrect() throws Exception {
        final RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/login?username=notExists&password=strongandcomplicatedpassword")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("token").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("rejected"))
                .andReturn();
    }
}