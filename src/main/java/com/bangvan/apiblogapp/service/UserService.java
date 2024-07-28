package com.bangvan.apiblogapp.service;

import com.bangvan.apiblogapp.dto.request.RegisterRequest;
import com.bangvan.apiblogapp.dto.request.UserRequest;
import com.bangvan.apiblogapp.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse addUser(RegisterRequest request);

    UserResponse getUserById(String id);

    List<UserResponse> getAllUser();

    UserResponse updateUserById(String id, UserRequest request);

    String deleteUserById(String id);
}
