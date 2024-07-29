package com.bangvan.apiblogapp.repository;

import com.bangvan.apiblogapp.entity.Comment;
import com.bangvan.apiblogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,String> {
    List<Comment> findAllByPost(Post post);
}
