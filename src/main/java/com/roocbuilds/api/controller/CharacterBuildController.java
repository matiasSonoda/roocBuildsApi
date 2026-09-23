package com.roocbuilds.api.controller;

import com.roocbuilds.api.model.dto.CharacterBuildRequestDTO;
import com.roocbuilds.api.model.dto.CharacterBuildResponseDTO;
import com.roocbuilds.api.service.ICharacterBuildService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/builds")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CharacterBuildController {

    private final ICharacterBuildService buildService;

    @Value("${ADMIN_ADMIN_TOKEN}")
    private String secretAdminToken;


    @GetMapping("")
    public ResponseEntity<List<CharacterBuildResponseDTO>> getAllBuildCharacter(){
        return ResponseEntity.status(HttpStatus.OK).body(buildService.getAllBuildCharacter());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterBuildResponseDTO> getOneBuildCharacter(@PathVariable Long id){
        CharacterBuildResponseDTO response = buildService.getOneBuildCharacter(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("")
    public ResponseEntity<CharacterBuildResponseDTO> createBuild
            (@Valid @RequestBody CharacterBuildRequestDTO requestDTO) {

        // Delegamos la lógica al servicio
        CharacterBuildResponseDTO response = buildService.createBuild(requestDTO);

        // Devolvemos el código 201 (Created) y el objeto creado
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PatchMapping("/vote/{id}")
    public ResponseEntity<Integer> patchVote(@PathVariable Long id) {
        Integer newTotalVotes = buildService.patchVote(id);
        return ResponseEntity.ok(newTotalVotes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuild(@PathVariable Long id,
                                            @RequestHeader(value="X-admin-Token", required=false) String token) {
        if (token == null || !token.equals(secretAdminToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        buildService.deleteBuild(id);
        return ResponseEntity.noContent().build();
    }
}
