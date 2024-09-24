package com.express.infrasturcture.adapter.input.rest.common;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

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

        // HTTP 상태 코드 가져오기
        //response가 HttpServletResponse의 인스턴스인지 확인
        //HttpServletResponse는 응답의 상태 코드를 가지고 있는 객체이기 떄문에 getStatus() 메서드를 사용해서 상태코드 가져옴
        int statusCode = response instanceof HttpServletResponse
                ? ((HttpServletResponse) response).getStatus()
                : HttpStatus.OK.value();

        // 응답 값을 커스텀 포맷으로 감싸기
        return new CustomResponse<>(statusCode, "Success", body);
    }


}
