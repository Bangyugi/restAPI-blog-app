package com.bangvan.apiblogapp.controller;

import com.bangvan.apiblogapp.dto.request.CategoryRequest;
import com.bangvan.apiblogapp.dto.request.UserRequest;
import com.bangvan.apiblogapp.dto.response.APIResponse;
import com.bangvan.apiblogapp.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<APIResponse> addCategory(@Valid @RequestBody CategoryRequest request){
        APIResponse apiResponse = APIResponse.success(categoryService.addCategory(request));
        apiResponse.setCode(201);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<APIResponse> getAllCategory(){
        APIResponse apiResponse = APIResponse.success(categoryService.getAllCategory());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getCategoryById(@PathVariable String id){
        APIResponse apiResponse = APIResponse.success(categoryService.getCategoryById(id));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateCategoryById(@PathVariable String id,@Valid @RequestBody CategoryRequest request){
        APIResponse apiResponse = APIResponse.success(categoryService.updateCategory(id, request));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteCategoryById (@PathVariable String id){
        APIResponse apiResponse = APIResponse.success(categoryService.deleteCategory(id));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
