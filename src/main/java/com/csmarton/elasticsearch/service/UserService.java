package com.csmarton.elasticsearch.service;

import com.csmarton.elasticsearch.model.CreateUserRequest;
import com.csmarton.elasticsearch.model.User;
import com.csmarton.elasticsearch.model.UserSearchResponse;

import java.util.List;

public interface UserService {
    User createUser(CreateUserRequest userRequest);

    UserSearchResponse searchUser(String firstName, String lastname);

}
