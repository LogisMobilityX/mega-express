package com.express.adapter.input.rest.responseTemplate;

import lombok.Getter;

@Getter
public class ResultDataResponse<T> extends ResponseTemplate  {

    private final T data;

    ResultDataResponse(T data) {
        super(true, ErrorCode.OK.getCode());
        this.data = data;
    }

    ResultDataResponse(boolean success, int statusCode ,String message,T data) {
        super(success, statusCode, message);
        this.data = data;
    }

    ResultDataResponse(T data, String message) {
        super(true, ErrorCode.OK.getCode(), message);
        this.data = data;
    }

    private ResultDataResponse(int statusCode, String message,T data) {
        super(true, statusCode, message);
        this.data = data;
    }

    public static <T> ResultDataResponse<T> of(T data) {
        return new ResultDataResponse<>(data);
    }

    public static <T> ResultDataResponse<T> of(T data, String message) {
        return new ResultDataResponse<>(data, message);
    }

    public static <T> ResultDataResponse<T> empty() {
        return new ResultDataResponse<>(null);
    }
}


