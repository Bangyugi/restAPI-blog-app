package com.bangvan.apiblogapp.exception;

import com.bangvan.apiblogapp.dto.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // handle specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest)
    {
        APIResponse apiResponse = APIResponse.error(404, exception.getMessage());
        webRequest.getDescription(false);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<APIResponse> handleBlogAPIException(BlogAPIException exception,WebRequest webRequest){
        APIResponse apiResponse = APIResponse.error(exception.getErrorCode().getCode(), exception.getErrorCode().getMessage());
        webRequest.getDescription(false);
        return new ResponseEntity<>(apiResponse,exception.getErrorCode().getStatus());

    }

    // global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse> handleGlobalException(Exception exception,WebRequest webRequest){
        APIResponse apiResponse = APIResponse.error(500, exception.getMessage());
        webRequest.getDescription(false);
        return new ResponseEntity<>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
