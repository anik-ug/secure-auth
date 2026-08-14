package com.anik.secureauth.service;

import com.anik.secureauth.dto.AdminUserResponse;
import com.anik.secureauth.dto.UpdateRoleRequest;

import java.util.List;

public interface AdminService {

    List<AdminUserResponse> getAllUsers();

    void updateUserRole(Long id, UpdateRoleRequest request);

    void deleteUser(Long id);
}