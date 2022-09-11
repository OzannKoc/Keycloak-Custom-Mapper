package com.user.service.Impl;

import com.user.model.KeycloakTokenResponse;
import com.user.service.KeycloakService;
import com.user.utils.ObjectMapperUtil;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KeycloakServiceImpl implements KeycloakService {


    @Value(value = "${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String client;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${client.keycloak.admin.username}")
    private String clientAdmin;

    @Value("${client.keycloak.admin.password}")
    private String clientAdminPassword;

    @Override
    public KeycloakTokenResponse loginWithKeycloak(String userName, String password) {
        return getAccessToken(createKeycloakBuilder(userName, password).build());
    }

    private KeycloakBuilder createKeycloakBuilder(String userNAME, String password) {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(client).clientSecret(clientSecret)
                .username(userNAME)
                .password(password)
                .grantType(OAuth2Constants.PASSWORD);
    }

    private KeycloakTokenResponse getAccessToken(Keycloak keycloak) {
        try {
            return ObjectMapperUtil.objectMapper(keycloak.tokenManager().getAccessToken(), KeycloakTokenResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.fillInStackTrace());
        }
    }
}
