package com.express.domain.model.company;

import org.springframework.web.multipart.MultipartFile;

public class BusinessNumberFile {
    private final MultipartFile file;

    public static BusinessNumberFile from(MultipartFile file){
        return new BusinessNumberFile(file);
    }

    private BusinessNumberFile(MultipartFile file) {
        this.file = file;
    }
}
