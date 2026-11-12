package com.mvdact4_t4.aplicacion_autenticacion.dto;

public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() { 
        return token; 
    
    }
    public void setToken(String token) { 
        this.token = token; 
    }
}
