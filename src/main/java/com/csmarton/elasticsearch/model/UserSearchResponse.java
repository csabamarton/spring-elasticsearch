package com.csmarton.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchResponse {

    private Integer total;
    private List<UserResponse> users;
}
