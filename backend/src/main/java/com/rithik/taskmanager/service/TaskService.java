package com.rithik.taskmanager.service;

import com.rithik.taskmanager.model.Task;
import com.rithik.taskmanager.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(Task task) {
        validate(task);
        return repository.save(task);
    }

    public Page<Task> getAll(Boolean isCompleted, Pageable pageable) {
        if (isCompleted != null) {
            return repository.findByIsCompleted(isCompleted, pageable);
        }
        return repository.findAll(pageable);
    }

    public Task getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task update(Integer id, Task updated) {
        Task existing = getById(id);
        validate(updated);

        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setDueDate(updated.getDueDate());
        existing.setIsCompleted(updated.getIsCompleted());

        return repository.save(existing);
    }

    public void delete(Integer id) {
        Task task = getById(id);
        repository.delete(task);
    }

    public Task toggle(Integer id) {
        Task task = getById(id);
        task.setIsCompleted(!task.getIsCompleted());
        return repository.save(task);
    }

    private void validate(Task task) {
        if (task.getTitle() == null || task.getTitle().trim().isEmpty())
            throw new IllegalArgumentException("Title is required");

        if (task.getTitle().length() > 100)
            throw new IllegalArgumentException("Title max length is 100");
    }
}