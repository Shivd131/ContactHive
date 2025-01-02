package com.contact_hive.contact_hive.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    public String uploadImage(MultipartFile contactImage);

    public List<String> allFiles();

    public String preSignedUrl(String filename);

    public String getImageUrlByName(String fileName);

}
