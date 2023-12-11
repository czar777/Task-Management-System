package com.test.taskmanagementsystem.security.context;

import com.test.taskmanagementsystem.security.UserSecurity;

public interface IAuthenticationFacade {
    UserSecurity getAuthentication();
}
