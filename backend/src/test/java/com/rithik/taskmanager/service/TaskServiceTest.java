package com.customer.taskmanager.service;

import com.customer.taskmanager.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    void shouldCreateTaskSuccessfully() {

        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Testing service layer");
        task.setIsCompleted(false);

        Task savedTask = taskService.create(task);

        assertNotNull(savedTask);
        assertNotNull(savedTask.getId());
        assertEquals("Test Task", savedTask.getTitle());
        assertFalse(savedTask.getIsCompleted());
    }

    @Test
    void shouldThrowExceptionWhenTitleIsMissing() {

        Task task = new Task();
        task.setDescription("No title provided");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.create(task);
        });

        assertEquals("Title is required", exception.getMessage());
    }

    @Test
    void shouldToggleCompletionStatus() {

        Task task = new Task();
        task.setTitle("Toggle Test");

        Task saved = taskService.create(task);

        Task toggled = taskService.toggle(saved.getId());

        assertTrue(toggled.getIsCompleted());
    }

    @Test
    void shouldDeleteTask() {

        Task task = new Task();
        task.setTitle("Delete Test");

        Task saved = taskService.create(task);

        taskService.delete(saved.getId());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            taskService.getById(saved.getId());
        });

        assertEquals("Task not found", exception.getMessage());
    }
}
