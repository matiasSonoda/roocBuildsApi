package com.roocbuilds.api.service;

import com.roocbuilds.api.model.dto.CharacterBuildRequestDTO;
import com.roocbuilds.api.model.dto.CharacterBuildResponseDTO;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ICharacterBuildService {

    CharacterBuildResponseDTO createBuild(@Valid CharacterBuildRequestDTO requestDTO);

    void deleteBuild(Long id);

    Integer patchVote(Long id);

    @Nullable List<CharacterBuildResponseDTO> getBuildCharacter();
}
