package com.bangvan.apiblogapp.repository;

import com.bangvan.apiblogapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
