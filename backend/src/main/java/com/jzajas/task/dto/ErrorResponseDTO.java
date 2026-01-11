package com.jzajas.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public class ErrorResponseDTO {
    private String message;
    private int status;
}
