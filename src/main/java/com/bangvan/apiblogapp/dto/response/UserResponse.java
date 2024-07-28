package com.bangvan.apiblogapp.dto.response;

import com.bangvan.apiblogapp.entity.Post;
import com.bangvan.apiblogapp.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String imageUrl;
    private String facebook;
    private String Youtube;
    private String instagram;
    private String spotify;

    private Set<RoleResponse> roles;
}
