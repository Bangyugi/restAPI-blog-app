package com.bangvan.apiblogapp.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlogAPIException extends RuntimeException{
    private ErrorCode errorCode;

    public BlogAPIException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
