package com.bangvan.apiblogapp.service.impl;

import com.bangvan.apiblogapp.dto.request.RoleRequest;
import com.bangvan.apiblogapp.dto.response.RoleResponse;
import com.bangvan.apiblogapp.entity.Role;
import com.bangvan.apiblogapp.exception.ResourceNotFoundException;
import com.bangvan.apiblogapp.repository.RoleRepository;
import com.bangvan.apiblogapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Override
    public RoleResponse addRole(RoleRequest request){
        Role role = modelMapper.map(request,Role.class);
        roleRepository.save(role);
        return modelMapper.map(role,RoleResponse.class);
    }

    @Override
    public List<RoleResponse> getAllRole(){
        return roleRepository.findAll().stream().map(result->modelMapper.map(result,RoleResponse.class)).collect(Collectors.toList());
    }

    @Override
    public RoleResponse updateRoleById(String id, RoleRequest request){
        Role role = roleRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Role", "id", id));
        modelMapper.map(request,role);
        return modelMapper.map(roleRepository.save(role),RoleResponse.class);
    }

    @Override
    public String deleteRoleById(String id){
        Role role = roleRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Role", "id", id));
        roleRepository.delete(role);
        return "Role with id: " + id + "was deleted successfully";

    }
}
