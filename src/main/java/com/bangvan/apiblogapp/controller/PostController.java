package com.bangvan.apiblogapp.controller;

import com.bangvan.apiblogapp.dto.request.PostRequest;
import com.bangvan.apiblogapp.dto.request.RegisterRequest;
import com.bangvan.apiblogapp.dto.request.UserRequest;
import com.bangvan.apiblogapp.dto.response.APIResponse;
import com.bangvan.apiblogapp.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping()
    public ResponseEntity<APIResponse> addPost(@Valid @RequestBody PostRequest request)
    {
        APIResponse apiResponse = APIResponse.success(postService.addPost(request));
        apiResponse.setCode(201);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<APIResponse> getAllPost(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "createAt", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir
    ){
        APIResponse apiResponse = APIResponse.success(postService.getAllPost(pageNo,pageSize,sortBy,sortDir));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getPostById(@PathVariable String id){
        APIResponse apiResponse = APIResponse.success(postService.getPostById(id));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updatePostById(@PathVariable String id,@Valid @RequestBody PostRequest request){
        APIResponse apiResponse = APIResponse.success(postService.updatePostById(id, request));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deletePostById (@PathVariable String id){
        APIResponse apiResponse = APIResponse.success(postService.deletePostById(id));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
