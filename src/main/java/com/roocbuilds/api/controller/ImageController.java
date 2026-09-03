package com.roocbuilds.api.controller;

import com.roocbuilds.api.service.IImageService;
import com.roocbuilds.api.service.ImageServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ImageController {

    private final IImageService imageService;

    @PostMapping("")
    public ResponseEntity<Map<String,String>> saveImage(@RequestParam("file") MultipartFile file){

        String urlDeLImagenGuardada = imageService.saveImage(file);

        Map<String,String > response = new HashMap<>();
        response.put("url", urlDeLImagenGuardada);

        return ResponseEntity.ok(response);
    }
}
