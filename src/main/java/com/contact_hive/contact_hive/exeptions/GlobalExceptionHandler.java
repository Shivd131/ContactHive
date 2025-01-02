package com.contact_hive.contact_hive.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.contact_hive.contact_hive.helpers.CustomResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ImageUploadException.class)
    public ResponseEntity<CustomResponse<Void>> handleImageUploadException(ImageUploadException e) {
        CustomResponse<Void> response = CustomResponse.<Void>builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .success(false)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}