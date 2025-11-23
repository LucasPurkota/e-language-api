package com.tcc.e_language_api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

public class ResetPasswordConfirmRequest {
    @NotBlank
    @Schema(example = "550e8400-e29b-41d4-a716-446655440000", description = "Token de verificação")
    private String token;

    @NotBlank
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @Schema(example = "novaSenha123", description = "Nova senha")
    private String newPassword;

    public ResetPasswordConfirmRequest() {}

    public ResetPasswordConfirmRequest(String token, String newPassword) {
        this.token = token;
        this.newPassword = newPassword;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}
