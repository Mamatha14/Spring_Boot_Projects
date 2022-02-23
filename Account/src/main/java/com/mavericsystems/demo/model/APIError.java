package com.mavericsystems.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class APIError {
    private LocalDateTime timeStamp;
    private HttpStatus status;
    private List<String> errors;
    private String msg;
    private String path;
}