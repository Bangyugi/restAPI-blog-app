package com.bangvan.apiblogapp.service.impl;

import com.bangvan.apiblogapp.dto.request.LoginRequest;
import com.bangvan.apiblogapp.dto.request.RegisterRequest;
import com.bangvan.apiblogapp.dto.response.UserResponse;
import com.bangvan.apiblogapp.entity.Role;
import com.bangvan.apiblogapp.entity.User;
import com.bangvan.apiblogapp.exception.BlogAPIException;
import com.bangvan.apiblogapp.exception.ErrorCode;
import com.bangvan.apiblogapp.repository.RoleRepository;
import com.bangvan.apiblogapp.repository.UserRepository;
import com.bangvan.apiblogapp.security.JWTTokenProvider;
import com.bangvan.apiblogapp.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public String login (LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String register(RegisterRequest registerRequest){
        if (userRepository.existsByUsername(registerRequest.getUsername()))
        {
            throw new BlogAPIException(ErrorCode.USERNAME_EXISTS);
        }
        if (userRepository.existsByEmail(registerRequest.getEmail()))
        {
            throw new BlogAPIException(ErrorCode.EMAIL_EXISTS);
        }
        User user = modelMapper.map(registerRequest,User.class);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return "User registered successfully!.";
    }
}
