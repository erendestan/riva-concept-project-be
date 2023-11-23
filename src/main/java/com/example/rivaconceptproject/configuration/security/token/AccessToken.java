package com.example.rivaconceptproject.configuration.security.token;

public interface AccessToken {

    Long getUserId();

    String getEmail();

    String getRoles();

    boolean hasRole(String roleName);
}
