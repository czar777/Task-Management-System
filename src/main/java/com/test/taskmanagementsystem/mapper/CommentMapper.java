package com.test.taskmanagementsystem.mapper;

import com.test.taskmanagementsystem.dto.CommentDto;
import com.test.taskmanagementsystem.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "task.id", target = "taskId")
    CommentDto toDto(Comment comment);

    @Mapping(source = "authorId", target = "author.id")
    @Mapping(source = "taskId", target = "task.id")
    Comment toEntity(CommentDto commentDto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    void update(CommentDto commentDto, @MappingTarget Comment comment);
}
