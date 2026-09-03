package com.roocbuilds.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class ImageServiceImp implements IImageService{

    private final String IMAGES_DIR= "images/";

    @Override
    public String saveImage(MultipartFile file) {
        try{
            Path imagesPath = Path.of(IMAGES_DIR);
            if (!Files.exists(imagesPath)){
                Files.createDirectories(imagesPath);
            }

            String originalFileName = file.getOriginalFilename();
            String extension = "";
            if (originalFileName != null && originalFileName.contains(".")){
                extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }

            String uniqueFilename = UUID.randomUUID().toString() + extension;

            Path filePath = imagesPath.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "http://localhost:8080/images/" + uniqueFilename;

        }catch(IOException ex){
            throw new RuntimeException("Error al guardar la imagen en el servidor", ex);
        }
    }
}
