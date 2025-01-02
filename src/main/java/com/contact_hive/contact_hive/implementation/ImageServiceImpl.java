package com.contact_hive.contact_hive.implementation;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.contact_hive.contact_hive.exeptions.ImageUploadException;
import com.contact_hive.contact_hive.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${app.s3.bucket}")
    private String bucketName;

    @Override
    public String uploadImage(MultipartFile contactImage) {
        String actualFileName = contactImage.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "_" + actualFileName;

        // metadata
        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentLength(contactImage.getSize());
        try {
            PutObjectResult putObjectResult = amazonS3
                    .putObject(new PutObjectRequest(bucketName, fileName, contactImage.getInputStream(), metaData));
            System.out.println("\n Image uploaded to S3");
            System.out.println("\n Image URL: " + putObjectResult.getMetadata().getUserMetaDataOf("url"));
            return fileName;
        } catch (IOException e) {
            throw new ImageUploadException("Error uploading image " + e.getMessage());
        }
        // return url of the uploaded image
    }

    @Override
    public List<String> allFiles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'allFiles'");
    }

    @Override
    public String preSignedUrl() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'preSignedUrl'");
    }

}
