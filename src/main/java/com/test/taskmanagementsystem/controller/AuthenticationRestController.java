package com.test.taskmanagementsystem.controller;


import com.test.taskmanagementsystem.dto.AuthenticationRequestDto;
import com.test.taskmanagementsystem.dto.UserDto;
import com.test.taskmanagementsystem.exception.BadCredentialsException;
import com.test.taskmanagementsystem.security.jwt.JwtTokenProvider;
import com.test.taskmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationRestController {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody AuthenticationRequestDto authentication) {
        UserDto user = userService.findByEmail(authentication.getUsername());


        if (!user.getPassword().equals(authentication.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
    }
}



