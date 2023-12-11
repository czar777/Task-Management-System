package com.test.taskmanagementsystem.service;

import com.test.taskmanagementsystem.dto.UserDto;
import com.test.taskmanagementsystem.entity.User;
import com.test.taskmanagementsystem.enums.Role;
import com.test.taskmanagementsystem.exception.BadCredentialsException;
import com.test.taskmanagementsystem.exception.EntityNotFoundException;
import com.test.taskmanagementsystem.mapper.UserMapper;
import com.test.taskmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserDto register(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EntityNotFoundException("User with email: " + userDto.getEmail() + " already exists");
        }

        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(Role.ROLE_USER));

        User registerUser = userRepository.save(user);
        UserDto registerUserDto = userMapper.toDto(registerUser);

        log.info("User registered: {}", registerUser);
        return registerUserDto;
    }

    @Transactional(readOnly = true)
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("User not found by email: " + email));

        UserDto userDto = userMapper.toDto(user);
        log.info("User found by email: {}", user);
        return userDto;
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found by id: " + id));

        UserDto userDto = userMapper.toDto(user);
        log.info("User found by id: {}", user);
        return userDto;
    }

    public void existsUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found by id: " + id);
        }
        log.info("User exists by id: {}", id);
    }

    @Transactional(readOnly = true)
    public void deleteById(Long id) {
        userRepository.deleteById(id);
        log.info("User deleted by id: {}", id);
    }
}
