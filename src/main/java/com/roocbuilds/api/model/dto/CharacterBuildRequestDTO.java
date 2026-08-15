package com.roocbuilds.api.model.dto;

import com.roocbuilds.api.model.enums.BuildType;
import com.roocbuilds.api.model.enums.JobClass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record CharacterBuildRequestDTO(
        @NotBlank(message = "The title is required")
        @Size(max = 100, message = "The title cannot exceed 100 characters")
        String title,
        @NotNull(message = "You must specify the job class")
        JobClass jobClass,
        @NotNull(message = "You must specify the build type")
        BuildType buildType,
        @Size(max=255, message = "The description is too long")
        String description,
        @NotBlank(message = "The build content cannot be  empty")
        String content) {
}
