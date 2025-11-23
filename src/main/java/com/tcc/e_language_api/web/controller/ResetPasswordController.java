package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.service.ResetPasswordService;
import com.tcc.e_language_api.web.docs.ResetSenha;
import com.tcc.e_language_api.web.dto.ResetPasswordConfirmRequest;
import com.tcc.e_language_api.web.dto.ResetPasswordRequest;
import com.tcc.e_language_api.web.dto.ResetPasswordResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reset-password")
public class ResetPasswordController implements ResetSenha {

    private final ResetPasswordService resetPasswordService;

    public ResetPasswordController(ResetPasswordService resetPasswordService) {
        this.resetPasswordService = resetPasswordService;
    }

    @PostMapping("/verify")
    public ResponseEntity<ResetPasswordResponse> verifyIdentity(@RequestBody @Valid ResetPasswordRequest request) {
        ResetPasswordResponse response = resetPasswordService.verifyAndGenerateToken(
            request.getEmail(), 
            request.getCpfDigits()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmNewPassword(@RequestBody @Valid ResetPasswordConfirmRequest request) {
        resetPasswordService.resetPassword(request.getToken(), request.getNewPassword());
        return ResponseEntity.ok("Senha alterada com sucesso!");
    }
}