package com.roocbuilds.api.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponseDTO(int status,
                               String error,
                               List<String> messages,
                               LocalDateTime timeStamp)
{}
