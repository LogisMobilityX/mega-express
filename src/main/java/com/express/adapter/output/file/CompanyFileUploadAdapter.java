package com.express.adapter.output.file;


import com.express.adapter.output.file.s3.S3Uploader;
import com.express.application.port.output.file.FileUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class CompanyFileUploadAdapter implements FileUploader {

    @Value("${aws.s3.company-business-number-bucket-name}")
    private String businessNumberBucketName;

    private final S3Uploader s3Uploader;

    @Override
    public String upload(MultipartFile file) {
        return s3Uploader.uploadFile(file,businessNumberBucketName);
    }

    @Override
    public void remove(MultipartFile file) {

    }
}
