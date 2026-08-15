package com.roocbuilds.api.model.dto;

import com.roocbuilds.api.model.enums.BuildType;
import com.roocbuilds.api.model.enums.JobClass;

import java.time.LocalDate;

public record CharacterBuildResponseDTO(
        Long id,
        String title,
        JobClass jobClass,
        BuildType buildType,
        Integer votes,
        String description,
        String content,
        LocalDate createdAt
) {
}
