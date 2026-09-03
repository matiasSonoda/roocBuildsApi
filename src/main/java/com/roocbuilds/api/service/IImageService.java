package com.roocbuilds.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageService {
    String saveImage(MultipartFile image);

}
