package com.express.adapter.input.rest.responseTemplate;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class CustomResponse<T> {
    private LocalDateTime dateTime;
    private int statusCode;
    private String message;
    private T data;

    public CustomResponse(int statusCode, String message, T data) {
        this.dateTime = LocalDateTime.now();
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
