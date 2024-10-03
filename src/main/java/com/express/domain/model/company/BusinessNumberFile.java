package com.express.domain.model.company;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class BusinessNumberFile {
    private final MultipartFile file;

    private String filePath;

    public static BusinessNumberFile from(MultipartFile file){
        return new BusinessNumberFile(file);
    }

    private BusinessNumberFile(MultipartFile file) {

        if(!isValidFile(file)) throw new RuntimeException();
        this.file = file;
    }

    public void uploadComplete(String filePath) {
        this.filePath = filePath;
    }

    private static boolean isValidFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null &&
            (contentType.equals("application/pdf") ||
                contentType.equals("image/jpeg") ||
                contentType.equals("image/png"));
    }
}
