package com.bangvan.apiblogapp.controller;

import com.bangvan.apiblogapp.dto.request.CommentRequest;
import com.bangvan.apiblogapp.dto.response.APIResponse;
import com.bangvan.apiblogapp.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<APIResponse> addComment(@PathVariable String postId,
                                                  @Valid @RequestBody CommentRequest request,
                                                  @RequestParam(value = "parentCommentId",required = false ) String parentCommentId){
        APIResponse apiResponse =APIResponse.success(commentService.addComment(postId,request,parentCommentId));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<APIResponse> getAllComment(@PathVariable String postId){
        APIResponse apiResponse = APIResponse.success(commentService.getAllComment(postId));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/{postId}/{commentId}")
    public  ResponseEntity<APIResponse> getCommentById(@PathVariable("postId") String postId, @PathVariable("commentId") String commentId){
        APIResponse apiResponse = APIResponse.success(commentService.getCommentById(postId,commentId));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PutMapping("/{postId}/{commentId}")
    public ResponseEntity<APIResponse> updateCommentById(@PathVariable("postId") String postId, @PathVariable("commentId") String commentId, @Valid @RequestBody CommentRequest request){
        APIResponse apiResponse = APIResponse.success(commentService.updateCommentById(postId,commentId,request));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<APIResponse> deleteCommentById(@PathVariable("postId") String postId, @PathVariable("commentId") String commentId){
        APIResponse apiResponse = APIResponse.success(commentService.deleteCommentById(postId,commentId));
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
