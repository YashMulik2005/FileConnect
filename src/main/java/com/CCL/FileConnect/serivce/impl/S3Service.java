package com.CCL.FileConnect.serivce.impl;

import com.CCL.FileConnect.exception.S3UploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

    @Value("${aws.s3.bucketName}")
    private String buket;

    @Value("${aws.s3.region}")
    private String region;

    @Autowired
    private S3Client s3Client;

    public String upload(MultipartFile file) {
        try {
            String key = UUID.randomUUID()+ "_" + file.getOriginalFilename();

            PutObjectRequest putObjectRequest =  PutObjectRequest.builder()
                    .bucket(buket)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            return key;
        }catch(IOException e) {
            throw new RuntimeException("File upload fail.", e);
        }catch(S3Exception e){
            throw new S3UploadException(e.getMessage());
        }
    }

    public String getUrl(String key){
        return "https://" + buket + ".s3." + region +".amazonaws.com/" + key;
    }
}
