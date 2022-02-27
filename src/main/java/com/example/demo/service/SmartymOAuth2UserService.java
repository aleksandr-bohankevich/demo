package com.example.demo.service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class SmartymOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        final List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("authority"));
        final Map<String, Object> attributes;
        try {
            attributes = SignedJWT.parse(accessToken.getTokenValue()).getJWTClaimsSet().getClaims();
        } catch (ParseException e) {
            throw new OAuth2AuthenticationException(e.getMessage());
        }
        return new DefaultOAuth2User(authorities, attributes, "name");
    }
}
