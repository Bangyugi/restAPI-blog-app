package com.bangvan.apiblogapp.service.impl;

import com.bangvan.apiblogapp.dto.request.CommentRequest;
import com.bangvan.apiblogapp.dto.response.CommentResponse;
import com.bangvan.apiblogapp.entity.Comment;
import com.bangvan.apiblogapp.entity.Post;
import com.bangvan.apiblogapp.entity.User;
import com.bangvan.apiblogapp.exception.BlogAPIException;
import com.bangvan.apiblogapp.exception.ErrorCode;
import com.bangvan.apiblogapp.exception.ResourceNotFoundException;
import com.bangvan.apiblogapp.repository.CommentRepository;
import com.bangvan.apiblogapp.repository.PostRepository;
import com.bangvan.apiblogapp.repository.UserRepository;
import com.bangvan.apiblogapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public CommentResponse addComment(String postId,CommentRequest request, String parentCommentId){
        Comment comment = modelMapper.map(request,Comment.class);
        User user = userRepository.findById(request.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User","id",request.getUserId()));

        comment.setUser(user);
        if (parentCommentId!=null){
            Comment parrentComment = commentRepository.findById(parentCommentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",parentCommentId));
            comment.setParentComment(parrentComment);
        }
        else {
            Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
            comment.setPost(post);
        }

        return modelMapper.map(commentRepository.save(comment),CommentResponse.class);

    }

    @Override
    public List<CommentResponse> getAllComment(String postId){
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        List<Comment> comments = commentRepository.findAllByPost(post);
        return comments.stream().map(result->modelMapper.map(result,CommentResponse.class)).collect(Collectors.toList());
    }


    @Override
    public CommentResponse getCommentById(String postId, String commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        if (comment.getPost()!=post)
        {
            throw new BlogAPIException(ErrorCode.COMMENT_NOT_BELONG_TO_POST);
        }
        return modelMapper.map(comment, CommentResponse.class);
    }

    @Override
    public CommentResponse updateCommentById(String postId, String commentId, CommentRequest request)
    {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));
        modelMapper.map(request,comment);
        return modelMapper.map(commentRepository.save(comment), CommentResponse.class);
    }


    @Override
    public String deleteCommentById(String postId,String commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));
        commentRepository.delete(comment);
        return "Comment with id: "+ commentId +" was deleted successfully!";

    }

}
