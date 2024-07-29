package com.bangvan.apiblogapp.dto.response;

import com.bangvan.apiblogapp.entity.Comment;
import com.bangvan.apiblogapp.entity.Post;
import com.bangvan.apiblogapp.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CommentResponse {
    private String id;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private User user;
    private Post post;
    private Comment parentComment;
    private List<Comment> childComments;
}
