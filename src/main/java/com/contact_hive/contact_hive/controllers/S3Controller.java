package com.contact_hive.contact_hive.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    public List<String> getAllFiles() {
        return imageService.allFiles();
    }

    @GetMapping("/{fileName}")
    public String urlByName(@PathVariable String fileName) {
        return imageService.getImageUrlByName(fileName);
    }

}
