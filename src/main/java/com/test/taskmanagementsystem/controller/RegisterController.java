package com.test.taskmanagementsystem.controller;

import com.test.taskmanagementsystem.dto.UserDto;
import com.test.taskmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping
    public UserDto register(@RequestBody UserDto userDto) {
        return userService.register(userDto);
    }
}
