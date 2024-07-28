package com.bangvan.apiblogapp.repository;

import com.bangvan.apiblogapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Role findByName(String roleUser);
}
