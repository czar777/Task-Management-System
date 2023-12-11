package com.test.taskmanagementsystem.security.context;

import com.test.taskmanagementsystem.security.UserSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    public UserSecurity getAuthentication() {
        return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
