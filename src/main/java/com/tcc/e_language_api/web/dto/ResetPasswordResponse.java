package com.tcc.e_language_api.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ResetPasswordResponse {
    @Schema(description = "Token temporário para resetar senha (válido por 15 minutos)")
    private String token;

    @Schema(description = "Mensagem de sucesso")
    private String message;

    public ResetPasswordResponse() {}

    public ResetPasswordResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
