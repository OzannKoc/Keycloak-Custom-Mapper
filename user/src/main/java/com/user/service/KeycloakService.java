package com.user.service;

import com.user.model.KeycloakTokenResponse;
import org.keycloak.representations.AccessTokenResponse;

public interface KeycloakService {

    KeycloakTokenResponse loginWithKeycloak(String userName, String password);
}
