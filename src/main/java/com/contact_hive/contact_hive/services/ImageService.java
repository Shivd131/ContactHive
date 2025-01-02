package com.contact_hive.contact_hive.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    public String uploadImage(MultipartFile contactImage);

}
