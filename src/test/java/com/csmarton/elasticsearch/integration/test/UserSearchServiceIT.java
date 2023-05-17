package com.csmarton.elasticsearch.integration.test;

import com.csmarton.elasticsearch.config.Config;
import com.csmarton.elasticsearch.config.TestConfig;
import com.csmarton.elasticsearch.model.CreateUserRequest;
import com.csmarton.elasticsearch.model.User;
import com.csmarton.elasticsearch.model.UserSearchResponse;
import com.csmarton.elasticsearch.repository.UserRepository;
import com.csmarton.elasticsearch.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {Config.class, TestConfig.class})
public class UserSearchServiceIT {

    @Autowired
    private UserService userService;

    private final static CreateUserRequest john = CreateUserRequest.builder()
            .firstName("John")
            .lastName("Doe")
            .email("john@gmail.com")
            .build();

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void setUp() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    void testCreateUserIndex() {

        User createdUser = userService.createUser(john);

        assertNotNull(createdUser);
        assertEquals(john.getFirstName(), createdUser.getFirstName());
        assertEquals(john.getLastName(), createdUser.getLastName());
        assertEquals(john.getEmail(), createdUser.getEmail());
        assertNotNull(createdUser.getUuid());
    }

    @Test
    void testFindByFirstName_Should_Match() {
        User createdUser = userService.createUser(john);
        UserSearchResponse userResponse = userService.searchUser("John", "Doe");

        assertNotNull(userResponse);
        assertNotNull(userResponse.getUsers());
        assertEquals(userResponse.getUsers().size(),1);
    }

    @Test
    void testFindByFirstName_Should_Not_Match() {
        UserSearchResponse userResponse = userService.searchUser("Alan", "Doe");

        assertNotNull(userResponse);
        assertNotNull(userResponse.getUsers());
        assertEquals(userResponse.getUsers().size(),0);

    }

   @AfterEach
    public void destroy() throws Exception {
       userRepository.deleteAll();
    }
}
