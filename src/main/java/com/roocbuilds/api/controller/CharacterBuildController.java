package com.roocbuilds.api.controller;

import com.roocbuilds.api.model.dto.CharacterBuildRequestDTO;
import com.roocbuilds.api.model.dto.CharacterBuildResponseDTO;
import com.roocbuilds.api.service.CharacterBuildService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/builds")
@RequiredArgsConstructor
public class CharacterBuildController {

    private final CharacterBuildService buildService;

    @PostMapping
    public ResponseEntity<CharacterBuildResponseDTO> createBuild
            (@Valid @RequestBody CharacterBuildRequestDTO requestDTO) {

        // Delegamos la lógica al servicio
        CharacterBuildResponseDTO response = buildService.createBuild(requestDTO);

        // Devolvemos el código 201 (Created) y el objeto creado
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
