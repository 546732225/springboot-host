package com.example.data.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

//
//    @ExceptionHandler(value = Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public RestResponse<Void> defaultErrorHandler(Exception ex, HttpServletRequest request) {
//        return RestResponse.error(500, "参数错误", ex.getClass(), request.getRequestURI());
//    }
}
