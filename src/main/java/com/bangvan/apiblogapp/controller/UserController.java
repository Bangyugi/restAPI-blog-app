package com.bangvan.apiblogapp.controller;

import com.bangvan.apiblogapp.dto.request.RegisterRequest;
import com.bangvan.apiblogapp.dto.request.UserRequest;
import com.bangvan.apiblogapp.dto.response.APIResponse;
import com.bangvan.apiblogapp.service.UserService;
import com.bangvan.apiblogapp.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<APIResponse> addUser(@Valid @RequestBody RegisterRequest request)
    {
        APIResponse apiResponse = APIResponse.success(userService.addUser(request));
        apiResponse.setCode(201);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<APIResponse> getAllUser (){
        APIResponse apiResponse = APIResponse.success(userService.getAllUser());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getUserById(@PathVariable String id){
        APIResponse apiResponse = APIResponse.success(userService.getUserById(id));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateUserById(@PathVariable String id,@Valid @RequestBody UserRequest request){
        APIResponse apiResponse = APIResponse.success(userService.updateUserById(id, request));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteUserById (@PathVariable String id){
        APIResponse apiResponse = APIResponse.success(userService.deleteUserById(id));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
