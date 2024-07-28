package com.bangvan.apiblogapp.dto.response;

import com.bangvan.apiblogapp.entity.Category;
import com.bangvan.apiblogapp.entity.Post;
import com.bangvan.apiblogapp.entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private String id;
    private String bannerURL;
    private String title;
    private String summary;
    private String publicationStatus;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String content;

    private User user;

    private Set<Category>categories;

    private List<Post> childPost;
}
