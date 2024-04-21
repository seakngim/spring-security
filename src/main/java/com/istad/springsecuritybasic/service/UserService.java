package com.istad.springsecuritybasic.service;

import com.istad.springsecuritybasic.model.dto.UserRequest;
import com.istad.springsecuritybasic.model.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
}
