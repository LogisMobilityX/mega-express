package com.express.application.port.output.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {

    String upload(MultipartFile file);

    void remove(MultipartFile file);
}
