package com.rithik.taskmanager.service;

import com.rithik.taskmanager.models.TaskVo;
import com.rithik.taskmanager.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TaskService {
    private static final Logger logger =
            LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskVo create(TaskVo task) {
        validate(task);
        return repository.save(task);
    }

    public Page<TaskVo> getAll(Boolean isCompleted, Pageable pageable) {
        if (isCompleted != null) {
            return repository.findByIsCompleted(isCompleted, pageable);
        }
        return repository.findAll(pageable);
    }

    public TaskVo getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public TaskVo update(Integer id, TaskVo updated) {
        TaskVo existing = getById(id);
        validate(updated);

        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setDueDate(updated.getDueDate());
        existing.setIsCompleted(updated.getIsCompleted());

        return repository.save(existing);
    }

    public void delete(Integer id) {
        TaskVo task = getById(id);
        repository.delete(task);
    }

    public TaskVo toggle(Integer id) {
        TaskVo task = getById(id);
        task.setIsCompleted(!task.getIsCompleted());
        return repository.save(task);
    }

    private void validate(TaskVo task) {
        if (task.getTitle() == null || task.getTitle().trim().isEmpty())
            throw new IllegalArgumentException("Title is required");

        if (task.getTitle().length() > 100)
            throw new IllegalArgumentException("Title max length is 100");
    }
}