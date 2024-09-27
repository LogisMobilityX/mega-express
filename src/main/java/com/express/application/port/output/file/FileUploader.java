package com.express.application.port.output.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {

    void upload(MultipartFile file);

    void remove(MultipartFile file);
}
