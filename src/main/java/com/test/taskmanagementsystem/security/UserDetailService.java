package com.test.taskmanagementsystem.security;

import com.test.taskmanagementsystem.dto.UserDto;
import com.test.taskmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userService.findByEmail(username);
        UserSecurity userSecurity = UserSecurity.toUserSecurity(userDto);

        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return userSecurity;
    }
}
