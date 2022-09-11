package com.keycloak.auth.controller;

import com.keycloak.auth.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permissions")
@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public List<String> findAllUserPermissions(@RequestParam("userId") String userId){
        System.out.println("User id: "+userId);
        return permissionService.getPermissions();
    }
}
