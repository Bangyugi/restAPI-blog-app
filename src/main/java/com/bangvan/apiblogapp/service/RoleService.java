package com.bangvan.apiblogapp.service;

import com.bangvan.apiblogapp.dto.request.RoleRequest;
import com.bangvan.apiblogapp.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse addRole(RoleRequest request);

    List<RoleResponse> getAllRole();

    RoleResponse updateRoleById(String id, RoleRequest request);

    String deleteRoleById(String id);
}
