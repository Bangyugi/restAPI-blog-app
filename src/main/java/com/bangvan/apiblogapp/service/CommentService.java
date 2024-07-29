package com.bangvan.apiblogapp.service;

import com.bangvan.apiblogapp.dto.request.CommentRequest;
import com.bangvan.apiblogapp.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {



    CommentResponse addComment(String postId, CommentRequest request, String parentCommentId);

    List<CommentResponse> getAllComment(String postId);

    CommentResponse getCommentById(String postId, String commentId);

    CommentResponse updateCommentById(String postId, String commentId, CommentRequest request);

    String deleteCommentById(String postId, String commentId);
}
