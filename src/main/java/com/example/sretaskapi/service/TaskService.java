package com.example.sretaskapi.service;

import com.example.sretaskapi.dto.CreateTaskRequest;
import com.example.sretaskapi.dto.UpdateTaskRequest;
import com.example.sretaskapi.entity.Task;
import com.example.sretaskapi.entity.TaskStatus;
import com.example.sretaskapi.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(TaskStatus.TODO);

        Task saved = taskRepository.save(task);

        log.info("Task created: id={}, title={}", saved.getId(), saved.getTitle());

        return saved;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found: " + id));
    }

    public Task update(Long id, UpdateTaskRequest request) {
        Task task = findById(id);

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());

        Task saved = taskRepository.save(task);

        log.info("Task updated: id={}, status={}", saved.getId(), saved.getStatus());

        return saved;
    }

    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);

        log.info("Task deleted: id={}", id);
    }
}