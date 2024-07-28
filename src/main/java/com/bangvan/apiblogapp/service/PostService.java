package com.bangvan.apiblogapp.service;

import com.bangvan.apiblogapp.dto.request.PostRequest;
import com.bangvan.apiblogapp.dto.response.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse addPost(PostRequest request);

    PostResponse getPostById(String id);


    List<PostResponse> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostResponse updatePostById(String id, PostRequest request);

    String deletePostById(String id);
}
