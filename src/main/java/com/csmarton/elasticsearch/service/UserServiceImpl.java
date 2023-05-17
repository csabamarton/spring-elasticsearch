package com.csmarton.elasticsearch.service;

import com.csmarton.elasticsearch.mapper.UserMapper;
import com.csmarton.elasticsearch.model.CreateUserRequest;
import com.csmarton.elasticsearch.model.User;
import com.csmarton.elasticsearch.model.UserResponse;
import com.csmarton.elasticsearch.model.UserSearchResponse;
import com.csmarton.elasticsearch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.NoSuchIndexException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ElasticSearchService elasticSearchService;
    private final UserMapper userMapper;

    @Override
    public User createUser(CreateUserRequest userRequest) {

        log.debug("Creating user - Begin");

        User user = this.userMapper.mapUserFromRequest(userRequest);

        User result = userRepository.save(user);
        log.debug("User created with uuid: {}", result.getUuid());

        log.debug("Creating user - End");

        return result;
    }

    @Override
    public UserSearchResponse searchUser(String firstName, String lastname) {
        log.debug("Searching User - Begin");

        List<User> users = null;

        try {
            users = elasticSearchService.searchByName(firstName, lastname);
        } catch (NoSuchIndexException e) {
            users = new ArrayList<>();

            log.debug("There is no index in the Elasticsearch");
        }

        log.debug("Result of user search: Found {} record(s)", users.size());

        UserSearchResponse userSearchResponse = createUserSearchResponse(users);

        log.debug("Searching User - End");

        return userSearchResponse;
    }

    private UserSearchResponse createUserSearchResponse(List<User> users) {
        UserSearchResponse userSearchResponse = new UserSearchResponse();

        userSearchResponse.setUsers(
                users.stream()
                        .map(user -> new UserResponse(user.getUuid(), user.getFirstName(), user.getLastName(), user.getEmail() ))
                        .collect(Collectors.toList()));

        userSearchResponse.setTotal(users.size());

        return userSearchResponse;
    }
}
