package com.roocbuilds.api.service;

import com.roocbuilds.api.model.dto.CharacterBuildRequestDTO;
import com.roocbuilds.api.model.dto.CharacterBuildResponseDTO;
import com.roocbuilds.api.model.entity.CharacterBuild;
import com.roocbuilds.api.repository.ICharacterBuildRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterBuildService implements  ICharacterBuildService{

    private final ICharacterBuildRepository buildRepository;

    public List<CharacterBuildResponseDTO> getBuildCharacter(){
        return buildRepository.findAll().stream().map(build -> {
            return new CharacterBuildResponseDTO(
                    build.getId(),
                    build.getTitle(),
                    build.getJobClass(),
                    build.getBuildType(),
                    build.getVotes(),
                    build.getDescription(),
                    build.getContent(),
                    build.getCreatedAt()
            );
        }).toList();
        };

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

    @Override
    public void deleteBuild(Long id) {
        buildRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Integer patchVote(Long id) {
        CharacterBuild build = buildRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("The build with ID " + id + "does not exist"));

        build.setVotes(build.getVotes() + 1);
        buildRepository.save(build);
        return build.getVotes();
    }
}
