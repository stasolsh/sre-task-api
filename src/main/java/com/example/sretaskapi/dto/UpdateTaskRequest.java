package com.example.sretaskapi.dto;

import com.example.sretaskapi.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;

public record UpdateTaskRequest(
        @NotBlank String title,
        String description,
        TaskStatus status
) {
}