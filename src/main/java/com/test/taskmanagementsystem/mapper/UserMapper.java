package com.test.taskmanagementsystem.mapper;

import com.test.taskmanagementsystem.dto.UserDto;
import com.test.taskmanagementsystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
