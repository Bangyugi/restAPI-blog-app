package com.bangvan.apiblogapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    USERNAME_EXISTS(403,"Username already exists", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTS(409,"Email already exists",HttpStatus.BAD_REQUEST),
    COMMENT_NOT_BELONG_TO_POST(404,"Comment not belong to this post", HttpStatus.BAD_REQUEST)
    ;
    private int code;
    private String message;
    private HttpStatus status;


}
