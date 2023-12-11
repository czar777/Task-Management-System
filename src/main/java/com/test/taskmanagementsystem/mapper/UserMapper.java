package com.test.taskmanagementsystem.mapper;

import com.test.taskmanagementsystem.dto.UserDto;
import com.test.taskmanagementsystem.entity.User;
import com.test.taskmanagementsystem.security.UserSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    UserSecurity toSecurity(UserDto userDto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    void update(UserDto userDto, @MappingTarget User user);
}
