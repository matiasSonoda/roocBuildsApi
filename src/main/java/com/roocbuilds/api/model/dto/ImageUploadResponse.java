package com.roocbuilds.api.model.dto;

public record ImageUploadResponse(
        String publicId,
        String feedUrl,
        String originalUrl,
        int width,
        int height
) {
}
