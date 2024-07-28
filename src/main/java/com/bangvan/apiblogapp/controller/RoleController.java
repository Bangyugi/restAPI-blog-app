package com.bangvan.apiblogapp.controller;

import com.bangvan.apiblogapp.dto.request.RoleRequest;
import com.bangvan.apiblogapp.dto.request.UserRequest;
import com.bangvan.apiblogapp.dto.response.APIResponse;
import com.bangvan.apiblogapp.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping()
    public ResponseEntity<APIResponse> addRole(@Valid @RequestBody RoleRequest request){
        APIResponse apiResponse = APIResponse.success(roleService.addRole(request));
        apiResponse.setCode(201);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<APIResponse> getAllRole(){
        APIResponse apiResponse = APIResponse.success(roleService.getAllRole());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateUserById(@PathVariable String id, @Valid  @RequestBody RoleRequest request){
        APIResponse apiResponse = APIResponse.success(roleService.updateRoleById(id, request));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> DeleteUserById (@PathVariable String id){
        APIResponse apiResponse = APIResponse.success(roleService.deleteRoleById(id));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
