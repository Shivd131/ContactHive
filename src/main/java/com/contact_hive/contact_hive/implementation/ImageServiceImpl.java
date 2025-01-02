package com.contact_hive.contact_hive.implementation;

import org.springframework.web.multipart.MultipartFile;

import com.contact_hive.contact_hive.services.ImageService;

public class ImageServiceImpl implements ImageService {

    @Override
    public String uploadImage(MultipartFile contactImage) {
        //return url of the uploaded image
        return "";
    }

}
