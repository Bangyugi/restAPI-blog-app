package com.bangvan.apiblogapp.service.impl;

import com.bangvan.apiblogapp.dto.request.RegisterRequest;
import com.bangvan.apiblogapp.dto.request.UserRequest;
import com.bangvan.apiblogapp.dto.response.UserResponse;
import com.bangvan.apiblogapp.entity.Role;
import com.bangvan.apiblogapp.entity.User;
import com.bangvan.apiblogapp.exception.BlogAPIException;
import com.bangvan.apiblogapp.exception.ErrorCode;
import com.bangvan.apiblogapp.exception.ResourceNotFoundException;
import com.bangvan.apiblogapp.repository.RoleRepository;
import com.bangvan.apiblogapp.repository.UserRepository;
import com.bangvan.apiblogapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;



    @Override
    public UserResponse addUser(RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername()))
        {
            throw new BlogAPIException(ErrorCode.USERNAME_EXISTS);
        }
        if (userRepository.existsByEmail(request.getEmail()))
        {
            throw new BlogAPIException(ErrorCode.EMAIL_EXISTS);
        }
        User user = modelMapper.map(request,User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        return modelMapper.map(userRepository.save(user),UserResponse.class);
    }

    @Override
    public UserResponse getUserById(String id){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        return modelMapper.map(user,UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUser(){
        return userRepository.findAll().stream().map(result->modelMapper.map(result,UserResponse.class)).collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUserById(String id, UserRequest request){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id", id));
        modelMapper.map(request,user);
        return modelMapper.map(userRepository.save(user),UserResponse.class);
    }

    @Override
    public String deleteUserById(String id){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
        return "User with id: " + id + " was deleted successfully";
    }
}
