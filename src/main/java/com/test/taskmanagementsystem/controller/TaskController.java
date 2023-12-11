package com.test.taskmanagementsystem.controller;

import com.test.taskmanagementsystem.dto.TaskDto;
import com.test.taskmanagementsystem.entity.Status;
import com.test.taskmanagementsystem.security.context.IAuthenticationFacade;
import com.test.taskmanagementsystem.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final IAuthenticationFacade authenticationFacade;
    private final TaskService taskService;

    @PostMapping
    public TaskDto create(@Valid @RequestBody TaskDto taskDto) {
        taskDto.setAuthorId(authenticationFacade.getAuthentication().getId());
        return taskService.create(taskDto);
    }

    @GetMapping("/{id}")
    public TaskDto get(@PathVariable long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @Valid @RequestBody TaskDto taskDto) {
        taskService.updateTask(id, taskDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        taskService.deleteById(id);
    }

    @PatchMapping("/status/{id}")
    public void changeStatus(@PathVariable long id, @RequestParam Status status) {
        taskService.changeStatus(id, status);
    }

    @PatchMapping("/executor/{id}")
    public void changeExecutor(@PathVariable long id, @RequestParam Long executorId) {
        taskService.changeExecutor(id, executorId);
    }
}
