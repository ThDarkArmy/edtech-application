//package com.firstproject.demo.service;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.UUID;
//
//@Service
//public class S3Service {
//
//    private final AmazonS3 s3Client;
//
//    @Value("${cloud.aws.s3.bucket}")
//    private String bucketName;
//
//    public S3Service(AmazonS3 s3Client) {
//        this.s3Client = s3Client;
//    }
//
//    public String uploadFile(MultipartFile file) throws IOException {
//        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentLength(file.getSize());
//        metadata.setContentType(file.getContentType());
//
//        s3Client.putObject(bucketName, fileName, file.getInputStream(), metadata);
//
//        return s3Client.getUrl(bucketName, fileName).toString();  // Return the file URL
//    }
//}
//
