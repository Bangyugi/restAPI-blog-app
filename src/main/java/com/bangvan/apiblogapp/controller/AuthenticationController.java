package com.bangvan.apiblogapp.controller;

import com.bangvan.apiblogapp.dto.request.LoginRequest;
import com.bangvan.apiblogapp.dto.request.RegisterRequest;
import com.bangvan.apiblogapp.dto.response.AuthenticationResponse;
import com.bangvan.apiblogapp.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
        String token = authenticationService.login(request);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken(token);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String>register(@RequestBody RegisterRequest request){
        String response = authenticationService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
