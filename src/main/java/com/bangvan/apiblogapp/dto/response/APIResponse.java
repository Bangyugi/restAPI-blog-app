package com.bangvan.apiblogapp.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {
    private int code;
    private String message;
    private Object result;

    public static APIResponse success(Object result){
        return new APIResponse(200, null, result);
    }
    public static APIResponse error(int code,String message){
        return new APIResponse(code, message,null);
    }
}
