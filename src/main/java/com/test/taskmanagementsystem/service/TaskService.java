package com.test.taskmanagementsystem.service;

import com.test.taskmanagementsystem.dto.TaskDto;
import com.test.taskmanagementsystem.entity.Status;
import com.test.taskmanagementsystem.entity.Task;
import com.test.taskmanagementsystem.entity.User;
import com.test.taskmanagementsystem.exception.AccessDeniedException;
import com.test.taskmanagementsystem.exception.EntityNotFoundException;
import com.test.taskmanagementsystem.mapper.TaskMapper;
import com.test.taskmanagementsystem.repository.TaskRepository;
import com.test.taskmanagementsystem.security.UserSecurity;
import com.test.taskmanagementsystem.security.context.IAuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final IAuthenticationFacade authenticationFacade;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserService userService;

    @Transactional
    public TaskDto create(TaskDto taskDto) {
        userService.existsUser(taskDto.getExecutorId());

        Task task = taskMapper.toEntity(taskDto);
        Task save = taskRepository.save(task);
        TaskDto saveDto = taskMapper.toDto(save);

        log.info("Task created: {}", saveDto);
        return saveDto;
    }

    @Transactional(readOnly = true)
    public TaskDto getTaskById(long id) {
        Task task = findTaskById(id);
        TaskDto taskDto = taskMapper.toDto(task);

        log.info("Task found by id: {}", task);
        return taskDto;
    }

    @Transactional
    public void updateTask(long id, TaskDto taskDto) {
        userService.existsUser(taskDto.getExecutorId());
        Task task = findTaskById(id);
        checkAccessDenied(task);
        taskMapper.update(taskDto, task);

        log.info("Task updated: {}", task);
    }

    @Transactional
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
        log.info("Task deleted by id: {}", id);
    }

    @Transactional
    public void changeStatus(long id, Status status) {
        Task task = findTaskById(id);
        checkAccessDenied(task);
        task.setStatus(status);

        log.info("Task status changed: {}", task);
    }

    @Transactional
    public void changeExecutor(long id, Long executorId) {
        Task task = findTaskById(id);
        userService.existsUser(executorId);
        task.setExecutor(User.builder().id(executorId).build());

        log.info("Task executor changed: {}", task);
    }

    private Task findTaskById(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found by id: " + id));
    }


    private void checkAccessDenied(Task task) {
        UserSecurity authentication = authenticationFacade.getAuthentication();
        if (!task.getAuthor().getId().equals(authentication.getId())) {
            throw new AccessDeniedException("Access denied");
        }
    }

    @Transactional(readOnly = true)
    public List<TaskDto> getAllTasks(Integer offset, Integer limit) {
        List<Task> tasks = taskRepository.findAll(PageRequest.of(offset, limit)).getContent();
        List<TaskDto> taskDtos = taskMapper.toDtoList(tasks);

        log.info("All tasks found (offset: {}, limit: {}): {}", offset, limit, tasks);
        return taskDtos;
    }

    @Transactional(readOnly = true)
    public List<TaskDto> getAllTasksByAuthorId(long id, int offset, int limit) {
        userService.existsUser(id);
        List<Task> tasks = taskRepository.findByAuthorId(id, PageRequest.of(offset, limit));

        log.info("Tasks by author found (offset: {}, limit: {}): {}", offset, limit, tasks);
        return taskMapper.toDtoList(tasks);

    }

    @Transactional(readOnly = true)
    public List<TaskDto> getAllTasksByExecutorId(long id, int offset, int limit) {
        userService.existsUser(id);
        List<Task> tasks = taskRepository.findByExecutorId(id, PageRequest.of(offset, limit));

        log.info("Tasks by executor found (offset: {}, limit: {}): {}", offset, limit, tasks);
        return taskMapper.toDtoList(tasks);
    }
}
