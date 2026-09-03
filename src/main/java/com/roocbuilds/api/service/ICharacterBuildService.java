package com.roocbuilds.api.service;

import com.roocbuilds.api.model.dto.CharacterBuildRequestDTO;
import com.roocbuilds.api.model.dto.CharacterBuildResponseDTO;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ICharacterBuildService {

    CharacterBuildResponseDTO createBuild(@Valid CharacterBuildRequestDTO requestDTO);

    void deleteBuild(Long id);

    Integer patchVote(Long id);

    @Nullable List<CharacterBuildResponseDTO> getAllBuildCharacter();

    CharacterBuildResponseDTO getOneBuildCharacter(Long id);

}
