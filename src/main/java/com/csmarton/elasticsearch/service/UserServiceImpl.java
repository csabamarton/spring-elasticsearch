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

        log.debug("Creating user - Begin");

        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .build();

        User result = userRepository.save(user);
        log.debug("User created with uuid: {}", result.getUuid());

        log.debug("Creating user - End");

        return result;
    }

    @Override
    public List<User> searchUser(String firstName, String lastname) {
        log.debug("Searching User - Begin");

        List<User> users = null;

        try {
            users = userRepository.findByFirstNameLikeAndLastNameLike(firstName, lastname);
        } catch (NoSuchIndexException e) {
            users = new ArrayList<>();

            log.debug("There is no index in the Elasticsearch");
        }

        log.debug("Result of user search: Found {} record(s)", users.size());

        log.debug("Searching User - End");

        return users;
    }
}
