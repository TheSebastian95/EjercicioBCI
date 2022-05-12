package com.challenge.sebastian.orellana.dto;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private String email;


    public JwtResponse(String accessToken, String username) {
        this.token = accessToken;
        this.username = username;
    }
}
