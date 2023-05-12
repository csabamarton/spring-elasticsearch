package com.csmarton.elasticsearch.service;

import com.csmarton.elasticsearch.model.CreateUserRequest;
import com.csmarton.elasticsearch.model.User;

import java.util.List;

public interface UserService {
    User createUser(CreateUserRequest userRequest);

    List<User> searchUser(String firstName, String lastname);

}
