package com.example.myshop.exception;

import com.example.myshop.constant.Status;
import com.example.myshop.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse<Object> response = ErrorResponse.builder()
                .status(Status.FAIL)
                .build();

        if (ex.hasFieldErrors()) {
            List<Error> errors = ex.getFieldErrors().stream()
                    .map(error -> new Error(StringUtil.camelCaseToSnakeCase(error.getField()), error.getDefaultMessage()))
                    .toList();
            response.setData(errors);
        }

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(response);
    }

    @ExceptionHandler(I18nException.class)
    public ResponseEntity<Object> handleI18nExceptionException(I18nException ex) {
        ErrorResponse<Object> response = ErrorResponse.builder()
                .status(Status.FAIL)
                .message(ex.getMessage())
                .build();

        return ResponseEntity
                .status(ex.getCode())
                .body(response);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleIOException(IOException ex) {
        ErrorResponse<Object> response = ErrorResponse.builder()
                .status(Status.FAIL)
                .message(ex.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
