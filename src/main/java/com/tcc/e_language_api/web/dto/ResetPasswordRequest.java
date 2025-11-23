package com.tcc.e_language_api.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;

public class ResetPasswordRequest {
    @NotBlank
    @Email
    @Schema(example = "user@example.com", description = "E-mail do usuário")
    private String email;

    @NotBlank
    @Pattern(regexp = "\\d{3}", message = "Devem ser exatamente 3 dígitos")
    @Schema(example = "123", description = "Primeiros 3 dígitos do CPF (pergunta de segurança)")
    private String cpfDigits;

    public ResetPasswordRequest() {}

    public ResetPasswordRequest(String email, String cpfDigits) { 
        this.email = email;
        this.cpfDigits = cpfDigits;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCpfDigits() { return cpfDigits; }
    public void setCpfDigits(String cpfDigits) { this.cpfDigits = cpfDigits; }
}