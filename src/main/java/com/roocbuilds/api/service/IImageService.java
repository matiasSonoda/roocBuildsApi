package com.roocbuilds.api.service;

import com.roocbuilds.api.model.dto.ImageUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    ImageUploadResponse saveImage(MultipartFile image);
}
