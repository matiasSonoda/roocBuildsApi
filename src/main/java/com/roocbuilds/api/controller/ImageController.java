package com.roocbuilds.api.controller;

import com.roocbuilds.api.model.dto.ImageUploadResponse;
import com.roocbuilds.api.service.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ImageController {

    private final IImageService imageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageUploadResponse> saveImage(@RequestParam("file") MultipartFile file){
        ImageUploadResponse response = imageService.saveImage(file);
        System.out.println("Hola estoy en el service implement de Image saveImage()");
        return ResponseEntity.ok(response);
    }
}
