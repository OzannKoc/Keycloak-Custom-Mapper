package com.user.controller;

import com.user.model.KeycloakTokenResponse;
import com.user.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private KeycloakService keycloakService;

    @GetMapping("/login")
    public KeycloakTokenResponse login(@RequestParam("userName") String userName, @RequestParam("password") String password) {


        return keycloakService.loginWithKeycloak(userName, password);
    }
}
