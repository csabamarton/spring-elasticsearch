package com.csmarton.elasticsearch.controller;

import com.csmarton.elasticsearch.model.User;
import com.csmarton.elasticsearch.model.UserSearchResponse;
import com.csmarton.elasticsearch.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void searchUser_shouldReturn_UserSearchResponse() throws Exception {

        final User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@gmail.com")
                .build();

        UserSearchResponse response = new UserSearchResponse();

        when(userService.searchUser(any(String.class), any(String.class))).thenReturn(response);
        this.mockMvc.perform(
                post("/api/elasticsearch/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"John\", \"lastName\": \"Doe\", \"email\": \"john@gmail.com\" }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
