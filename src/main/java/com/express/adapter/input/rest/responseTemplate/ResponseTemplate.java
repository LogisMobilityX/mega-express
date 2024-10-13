package com.express.adapter.input.rest.responseTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class ResponseTemplate {
    private LocalDateTime timeStamp;
    private Boolean success;
    private Integer code;
    private String message;

    public ResponseTemplate(Boolean success, Integer code) {
        this.success = success;
        this.code = code;
    }

    public ResponseTemplate(Boolean success, int errorCode,String message) {
        this.timeStamp = LocalDateTime.now();
        this.success = success;
        this.code = errorCode;
        this.message = message;
    }
    public static ResponseTemplate of(Boolean success, ErrorCode code) {
        return new ResponseTemplate(success, code.getCode());
    }

    public static ResponseTemplate of(Boolean success, ErrorCode errorCode, Exception e) {
        return new ResponseTemplate(success, errorCode.getCode(), errorCode.getMessage(e));
    }

    public static ResponseTemplate of(Boolean success, ErrorCode errorCode, String message) {
        return new ResponseTemplate(success, errorCode.getCode(), errorCode.getMessage(message));
    }
}

