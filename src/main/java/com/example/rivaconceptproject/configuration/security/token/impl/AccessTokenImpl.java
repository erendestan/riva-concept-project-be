package com.example.rivaconceptproject.configuration.security.token.impl;

import com.example.rivaconceptproject.configuration.security.token.AccessToken;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class AccessTokenImpl implements AccessToken {
    private final String email;
    private final Long userId;
    private final String roles;

    public AccessTokenImpl(String email, Long userId, String roles){
        this.email = email;
        this.userId = userId;
        this.roles = roles;
    }

    @Override
    public boolean hasRole(String roleName) {
        return this.getRoles().contains(roleName);
    }
}
