package com.csmarton.elasticsearch.controller;

import com.csmarton.elasticsearch.model.*;
import com.csmarton.elasticsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/elasticsearch")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

   @PostMapping("/create")
   public ResponseEntity<UserResponse> saveUser(@RequestBody @Valid CreateUserRequest userRequest) {
       log.info("Create new user index - Controller", userRequest);

       User createdUser = userService.createUser(userRequest);

       UserResponse userResponse = new UserResponse(createdUser.getUuid(), createdUser.getFirstName(), createdUser.getLastName(), createdUser.getEmail());

       return ResponseEntity.ok().body(userResponse);
   }

    @PostMapping (path = {"/search"})
    public ResponseEntity<UserSearchResponse> searchUsers(@RequestBody  @Valid  UserSearchRequest userSearchRequest) {
        log.info("Search User - Controller", userSearchRequest);

        List<User> users = userService.searchUser(userSearchRequest.getFirstName(), userSearchRequest.getLastName());

        UserSearchResponse userSearchResponse = createUserSearchResponse(users);
        return ResponseEntity.ok().body(userSearchResponse);
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
