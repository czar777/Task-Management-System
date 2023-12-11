package com.test.taskmanagementsystem.repository;

import com.test.taskmanagementsystem.entity.Task;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAuthorId(Long authorId, PageRequest of);

    List<Task> findByExecutorId(Long executorId, PageRequest of);
}
