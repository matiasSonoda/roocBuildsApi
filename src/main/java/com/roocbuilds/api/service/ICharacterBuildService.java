package com.roocbuilds.api.service;

import com.roocbuilds.api.model.dto.CharacterBuildRequestDTO;
import com.roocbuilds.api.model.dto.CharacterBuildResponseDTO;
import jakarta.validation.Valid;

public interface ICharacterBuildService {

    public CharacterBuildResponseDTO createBuild(@Valid CharacterBuildRequestDTO requestDTO);
}
