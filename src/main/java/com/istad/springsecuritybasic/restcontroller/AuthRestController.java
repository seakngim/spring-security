package com.istad.springsecuritybasic.restcontroller;

import com.istad.springsecuritybasic.model.dto.UserRequest;
import com.istad.springsecuritybasic.model.dto.UserResponse;
import com.istad.springsecuritybasic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.istad.springsecuritybasic.utils.BaseResponse;

@RestController
@RequiredArgsConstructor
public class AuthRestController {
    private final UserService userService;

    @PostMapping("/register")
    public BaseResponse<UserResponse> createNewUser(@RequestBody UserRequest userRequest) {
        return BaseResponse.<UserResponse>createSuccess()
                .setPayload(userService.createUser(userRequest));

    }
}
