package com.test.taskmanagementsystem.mapper;

import com.test.taskmanagementsystem.dto.TaskDto;
import com.test.taskmanagementsystem.entity.Comment;
import com.test.taskmanagementsystem.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    @Mapping(source = "comments", target = "commentIds", qualifiedByName = "toIdList")
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "executor.id", target = "executorId")
    TaskDto toDto(Task task);

    @Mapping(source = "commentIds", target = "comments", qualifiedByName = "toEntityList")
    @Mapping(source = "authorId", target = "author.id")
    @Mapping(source = "executorId", target = "executor.id")
    Task toEntity(TaskDto taskDto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    void update(TaskDto taskDto, @MappingTarget Task task);

    @Named("toIdList")
    default List<Long> toIdList(List<Comment> tasks) {
        return tasks.stream().map(Comment::getId).toList();
    }

    @Named("toEntityList")
    default List<Comment> toEntityList(List<Long> commentIds) {
        return commentIds.stream().map(id -> Comment.builder().id(id).build()).toList();
    }
}
