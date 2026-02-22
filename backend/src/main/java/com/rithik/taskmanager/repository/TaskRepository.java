package com.rithik.taskmanager.repository;

import com.rithik.taskmanager.models.TaskVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TaskRepository extends JpaRepository<TaskVo, Integer> {
    Page<TaskVo> findByIsCompleted(Boolean isCompleted, Pageable pageable);
    Page<TaskVo> findByDueDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
}