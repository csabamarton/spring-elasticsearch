package com.csmarton.elasticsearch.service;

import com.csmarton.elasticsearch.model.CreateUserRequest;
import com.csmarton.elasticsearch.model.User;
import com.csmarton.elasticsearch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.NoSuchIndexException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(CreateUserRequest userRequest) {

        log.info("Creating user - Begin");

        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .build();

        User result = userRepository.save(user);

        log.info("Creating user - End");

        return result;
    }

    @Override
    public List<User> searchUser(String firstName, String lastname) {
        log.info("Searching User - Begin");

        List<User> users = null;

        try {
            users = userRepository.findByFirstNameLikeAndLastNameLike(firstName, lastname);
        } catch (NoSuchIndexException e) {
            users = new ArrayList<>();
        }

        log.info("Searching User - End");

        return users;
    }
}
