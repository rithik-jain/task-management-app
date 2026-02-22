package com.rithik.taskmanager.repository;

import com.rithik.taskmanager.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TaskRepository extends JpaRepository<TaskVo, Integer> {
    Page<TaskVo> findByIsCompleted(Boolean isCompleted, Pageable pageable);
    Page<Task> findByDueDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
}