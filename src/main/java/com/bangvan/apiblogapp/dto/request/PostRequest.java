package com.bangvan.apiblogapp.dto.request;

import com.bangvan.apiblogapp.entity.Category;
import com.bangvan.apiblogapp.entity.Post;
import com.bangvan.apiblogapp.entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
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
public class PostRequest {
    private String bannerURL;
    @NotNull(message = "Post's title is required!")
    private String title;
    private String summary;
    private String publicationStatus;
    @NotNull(message = "Post's content is required!")
    private String content;
    @NotNull(message = "Post's author is required!")
    private String userId;
    @NotNull(message = "Post's category is required!")
    private Set<String> categoryName;
    private List<String> relatedPost;
}
