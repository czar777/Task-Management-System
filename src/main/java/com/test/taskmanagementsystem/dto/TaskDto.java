package com.test.taskmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.taskmanagementsystem.entity.Priority;
import com.test.taskmanagementsystem.entity.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Schema(description = "Сущность задачи")
public class TaskDto {

    //    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "Heading is required")
    private String heading;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Status is required")
    private Status status;

    @NotNull(message = "Priority is required")
    private Priority priority;

    private Long authorId;

    @NotNull(message = "Executor is required")
    private Long executorId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Long> commentIds;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
}
