package com.customer.taskmanager.controller;

import com.rithik.taskmanager.model.Task;
import com.rithik.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @Operation(summary = "Create new task")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Task task) {
        try {
            return new ResponseEntity<>(service.create(task), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return error(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get all tasks with pagination")
    @GetMapping
    public ResponseEntity<Page<Task>> getAll(
            @RequestParam(required = false) Boolean isCompleted,
            Pageable pageable) {
        return ResponseEntity.ok(service.getAll(isCompleted, pageable));
    }

    @Operation(summary = "Get task by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (RuntimeException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update task")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody Task task) {
        try {
            return ResponseEntity.ok(service.update(id, task));
        } catch (RuntimeException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Toggle completion")
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<?> toggle(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.toggle(id));
        } catch (RuntimeException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete task")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<Map<String, Object>> error(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", LocalDateTime.now());
        map.put("status", status.value());
        map.put("error", message);
        return new ResponseEntity<>(map, status);
    }
}