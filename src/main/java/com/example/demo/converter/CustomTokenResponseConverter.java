package com.example.demo.converter;

import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;

public class CustomTokenResponseConverter implements
        Converter<Map<String, Object>, OAuth2AccessTokenResponse> {
    @Override
    public OAuth2AccessTokenResponse convert(Map<String, Object> tokenResponseParameters) {
        String accessToken = tokenResponseParameters.get(SmartymOAuth2ParameterNames.ACCESS_TOKEN).toString();
        long expiresIn = Long.valueOf(tokenResponseParameters.get(SmartymOAuth2ParameterNames.EXPIRES_IN).toString());

        OAuth2AccessToken.TokenType accessTokenType = OAuth2AccessToken.TokenType.BEARER;

        return OAuth2AccessTokenResponse.withToken(accessToken)
                .tokenType(accessTokenType)
                .expiresIn(expiresIn)
                .build();
    }

}