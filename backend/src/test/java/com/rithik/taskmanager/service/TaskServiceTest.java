package com.rithik.taskmanager.service;

import com.rithik.taskmanager.models.TaskVo;
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

        TaskVo task = new TaskVo();
        task.setTitle("Test Task");
        task.setDescription("Testing service layer");
        task.setIsCompleted(false);

        TaskVo savedTask = taskService.create(task);

        assertNotNull(savedTask);
        assertNotNull(savedTask.getId());
        assertEquals("Test Task", savedTask.getTitle());
        assertFalse(savedTask.getIsCompleted());
    }

    @Test
    void shouldThrowExceptionWhenTitleIsMissing() {

        TaskVo task = new TaskVo();
        task.setDescription("No title provided");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.create(task);
        });

        assertEquals("Title is required", exception.getMessage());
    }

    @Test
    void shouldToggleCompletionStatus() {

        TaskVo task = new TaskVo();
        task.setTitle("Toggle Test");

        TaskVo saved = taskService.create(task);

        TaskVo toggled = taskService.toggle(saved.getId());

        assertTrue(toggled.getIsCompleted());
    }

    @Test
    void shouldDeleteTask() {

        TaskVo task = new TaskVo();
        task.setTitle("Delete Test");

        TaskVo saved = taskService.create(task);

        taskService.delete(saved.getId());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            taskService.getById(saved.getId());
        });

        assertEquals("Task not found", exception.getMessage());
    }
}
