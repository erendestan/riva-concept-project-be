package com.example.rivaconceptproject.configuration.security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
