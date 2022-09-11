package com.keycloak.auth.service.Impl;

import com.keycloak.auth.repository.PermissionRepository;
import com.keycloak.auth.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<String> getPermissions() {
        return permissionRepository.findPermissionsByIdIn(List.of(1L,2L));
    }
}
