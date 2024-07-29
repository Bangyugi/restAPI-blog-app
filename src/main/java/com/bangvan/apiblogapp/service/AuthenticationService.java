package com.bangvan.apiblogapp.service;

import com.bangvan.apiblogapp.dto.request.LoginRequest;
import com.bangvan.apiblogapp.dto.request.RegisterRequest;
import com.bangvan.apiblogapp.dto.response.UserResponse;

public interface AuthenticationService {
    String login (LoginRequest loginRequest);

    String register (RegisterRequest request);
}
