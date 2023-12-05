package com.test.taskmanagementsystem.service;

import com.test.taskmanagementsystem.dto.UserDto;
import com.test.taskmanagementsystem.entity.User;
import com.test.taskmanagementsystem.mapper.UserMapper;
import com.test.taskmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserDto register(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }
}
