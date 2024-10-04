package com.express.adapter.output.file.s3;

import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

@RequiredArgsConstructor
public class S3Uploader {

    private final S3Client s3Client;

    public String uploadFile(MultipartFile file,String bucketName) {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

            // S3에 파일 업로드
            PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest,
                Paths.get(file.getOriginalFilename()));

            return fileName; // 업로드된 파일의 S3 키 반환
        } catch (S3Exception e) {
            throw new RuntimeException("S3 업로드 중 오류 발생: " + e.getMessage());
        }
    }
}
