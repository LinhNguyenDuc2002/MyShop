package com.example.myshop.exception;

import com.example.myshop.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ErrorResponse<T> {
    private Status status;

    private String message;

    private T data;
}
