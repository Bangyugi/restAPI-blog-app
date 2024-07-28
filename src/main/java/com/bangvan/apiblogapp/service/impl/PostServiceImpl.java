package com.bangvan.apiblogapp.service.impl;

import com.bangvan.apiblogapp.dto.request.PostRequest;
import com.bangvan.apiblogapp.dto.response.PostResponse;
import com.bangvan.apiblogapp.entity.Category;
import com.bangvan.apiblogapp.entity.Post;
import com.bangvan.apiblogapp.entity.User;
import com.bangvan.apiblogapp.exception.ResourceNotFoundException;
import com.bangvan.apiblogapp.repository.CategoryRepository;
import com.bangvan.apiblogapp.repository.PostRepository;
import com.bangvan.apiblogapp.repository.UserRepository;
import com.bangvan.apiblogapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public PostResponse addPost(PostRequest request) {
        Post post = modelMapper.map(request, Post.class);
        User user = userRepository.findById(request.getUserId()).orElseThrow(()->new ResourceNotFoundException("User", "id", request.getUserId()));
        post.setUser(user);
        Set<Category> categories = new HashSet<>();
        for(String categoryName: request.getCategoryName())
        {
            Category category = categoryRepository.findByName(categoryName).orElseThrow(()->new ResourceNotFoundException("Category", "category's name",categoryName));
            categories.add(category);
        }
        post.setCategories(categories);

        post = postRepository.save(post);

        List<Post> relatedPosts = new ArrayList<>();
        for(String childPostId: request.getRelatedPost()){
            Post childPost = postRepository.findById(childPostId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", childPostId));
            relatedPosts.add(childPost);
        }

        post.setRelatedPost(relatedPosts);
        return modelMapper.map(postRepository.save(post), PostResponse.class);
    }

    @Override
    public PostResponse getPostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return modelMapper.map(post, PostResponse.class);
    }

    @Override
    public List<PostResponse> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        return postRepository.findAll(pageable).stream().map(result -> modelMapper.map(result, PostResponse.class)).collect(Collectors.toList());
    }

    @Override
    public PostResponse updatePostById(String id, PostRequest request) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        modelMapper.map(request, post);
        postRepository.save(post);
        return modelMapper.map(post, PostResponse.class);
    }

    @Override
    public String deletePostById(String id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
        return "Post with id: "+ id +" was deleted successfully!";
    }


}
