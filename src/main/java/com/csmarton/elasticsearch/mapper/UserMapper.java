package com.csmarton.elasticsearch.mapper;

import com.csmarton.elasticsearch.model.CreateUserRequest;
import com.csmarton.elasticsearch.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper
{
    User mapUserFromRequest(CreateUserRequest request);
}