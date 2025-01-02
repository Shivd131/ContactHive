package com.contact_hive.contact_hive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.contact_hive.contact_hive.services.ImageService;

@RestController
@RequestMapping("/api/v1/s3")
public class S3Controller {
    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile file) {
        return ResponseEntity.ok(imageService.uploadImage(file));

    }
}
