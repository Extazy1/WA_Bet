package com.handler;

import com.exception.PermissionDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<Map<String, Object>> handlePermissionDeniedException(PermissionDeniedException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("msg", ex.getMessage());
        response.put("code", ex.getCode());
        response.put("data", new HashMap<>());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    //添加更多异常处理方法
}
