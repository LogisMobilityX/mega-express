package com.express.adapter.input.rest.responseTemplate;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.ObjectUtils.isEmpty;

@ControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 모든 응답에 대해 적용
        return true;
    }


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        int statusCode = 0;
        //상태코드 세팅
        boolean errorCheck = body.toString().contains("success");
        if (errorCheck) {
            ExceptionResponseDTO responseDTO = (ExceptionResponseDTO) body;
            String errorMessage = String.valueOf(responseDTO.getMessage());
            if (responseDTO.getMessage().equals("Cannot invoke \"Object.toString()\" because \"body\" is null")){
                return new ResultDataResponse<>(true,HttpStatus.OK.value(), "SUCCESS",null);
            }
            statusCode = responseDTO.getCode();

            return new ResultDataResponse<>(false,statusCode,errorMessage,null);
        }else {
            //응답 값을 커스텀 포맷으로 감싸기
            return new ResultDataResponse<>(body, "SUCCESS");
        }
    }

}
