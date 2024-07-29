package com.bangvan.apiblogapp.dto.request;

import com.bangvan.apiblogapp.entity.Comment;
import com.bangvan.apiblogapp.entity.Post;
import com.bangvan.apiblogapp.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    @NotNull(message = "Comment's title is required!")
    private String title;
    @NotNull(message = "Comment's content is required!")
    private String content;
    @NotNull(message = "Comment's user is required!")
    private String userId;
}
