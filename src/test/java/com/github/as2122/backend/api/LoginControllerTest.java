package com.github.as2122.backend.api;

import com.github.as2122.backend.api.controllers.LoginController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
                .get("/login?username=user1&password=password1")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("token").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("accepted"))
                .andExpect(MockMvcResultMatchers.jsonPath("email").value("user1@nsn.pt"))
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