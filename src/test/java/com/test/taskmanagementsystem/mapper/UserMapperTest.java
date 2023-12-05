package com.test.taskmanagementsystem.mapper;

import com.test.taskmanagementsystem.dto.UserDto;
import com.test.taskmanagementsystem.entity.User;
import com.test.taskmanagementsystem.entity.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @Spy
    private UserMapperImpl userMapper;

    private UserDto userDto;
    private User user;

    @BeforeEach
    void setUp() {
        userDto = UserDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("a9bJn@example.com")
                .password("password")
                .roles(Set.of(Role.ROLE_USER))
                .build();

        user = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("a9bJn@example.com")
                .password("password")
                .roles(Set.of(Role.ROLE_USER))
                .build();
    }

    @Test
    void toDto() {
        UserDto actual = userMapper.toDto(user);
        assertEquals(userDto, actual);
    }

    @Test
    void toEntity() {
        User actual = userMapper.toEntity(userDto);
        assertEquals(user, actual);
    }
}