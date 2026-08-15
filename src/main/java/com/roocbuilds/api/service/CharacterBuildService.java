package com.roocbuilds.api.service;

import com.roocbuilds.api.model.dto.CharacterBuildRequestDTO;
import com.roocbuilds.api.model.dto.CharacterBuildResponseDTO;
import com.roocbuilds.api.model.entity.CharacterBuild;
import com.roocbuilds.api.repository.ICharacterBuildRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterBuildService implements  ICharacterBuildService{

    private final ICharacterBuildRepository buildRepository;

    public CharacterBuildResponseDTO createBuild( CharacterBuildRequestDTO requestDTO) {
        CharacterBuild characterBuild = new CharacterBuild();
        characterBuild.setTitle(requestDTO.title());
        characterBuild.setDescription(requestDTO.description());
        characterBuild.setContent(requestDTO.content());
        characterBuild.setJobClass(requestDTO.jobClass());
        characterBuild.setBuildType(requestDTO.buildType());

        CharacterBuild saveBuild = buildRepository.save(characterBuild);

        return new CharacterBuildResponseDTO(
                saveBuild.getId(),
                saveBuild.getTitle(),
                saveBuild.getJobClass(),
                saveBuild.getBuildType(),
                saveBuild.getVotes(),
                saveBuild.getDescription(),
                saveBuild.getContent(),
                saveBuild.getCreatedAt()
        );
    }
}
