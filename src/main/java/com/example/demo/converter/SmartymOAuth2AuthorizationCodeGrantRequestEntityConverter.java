package com.example.demo.converter;

import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.util.MultiValueMap;

public class SmartymOAuth2AuthorizationCodeGrantRequestEntityConverter extends OAuth2AuthorizationCodeGrantRequestEntityConverter {

    @Override
    protected MultiValueMap<String, String> createParameters(
            OAuth2AuthorizationCodeGrantRequest authorizationCodeGrantRequest) {
        MultiValueMap<String, String> parameters = super.createParameters(authorizationCodeGrantRequest);

        String scope = authorizationCodeGrantRequest.getClientRegistration().getScopes().toString();
        parameters.add("scope", scope);

        return parameters;
    }
}