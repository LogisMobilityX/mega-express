package com.express.domain.model.company;

import org.springframework.web.multipart.MultipartFile;

public class BusinessNumberFile {
    private final MultipartFile file;

    public static BusinessNumberFile from(MultipartFile file){
        return new BusinessNumberFile(file);
    }

    private BusinessNumberFile(MultipartFile file) {
        if(!isValidFile(file)) throw new RuntimeException();
        this.file = file;
    }

    private static boolean isValidFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null &&
            (contentType.equals("application/pdf") ||
                contentType.equals("image/jpeg") ||
                contentType.equals("image/png"));
    }
}
