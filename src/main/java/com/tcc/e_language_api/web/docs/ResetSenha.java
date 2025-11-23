package com.tcc.e_language_api.web.docs;

import com.tcc.e_language_api.web.dto.ResetPasswordConfirmRequest;
import com.tcc.e_language_api.web.dto.ResetPasswordRequest;
import com.tcc.e_language_api.web.dto.ResetPasswordResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Recuperação de Senha", description = "Endpoints para resetar senha usando pergunta de segurança (CPF)")
public interface ResetSenha {

    @Operation(
        summary = "Etapa 1: Verificar identidade com CPF",
        description = "Valida email e primeiros 3 dígitos do CPF. Retorna token temporário se correto."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Email e primeiros 3 dígitos do CPF",
        required = true,
        content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = "{ \"email\": \"user@example.com\", \"cpfDigits\": \"123\" }")
        )
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Verificação bem-sucedida - token retornado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "401", description = "CPF não corresponde")
    })
    ResponseEntity<ResetPasswordResponse> verifyIdentity(@RequestBody @Valid ResetPasswordRequest request);

    @Operation(
        summary = "Etapa 2: Confirmar nova senha",
        description = "Usa o token da etapa 1 para definir nova senha"
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Token e nova senha",
        required = true,
        content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = "{ \"token\": \"550e8400-e29b-41d4-a716-446655440000\", \"newPassword\": \"novaSenha123\" }")
        )
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Token inválido ou expirado"),
        @ApiResponse(responseCode = "404", description = "Token não encontrado")
    })
    ResponseEntity<String> confirmNewPassword(@RequestBody @Valid ResetPasswordConfirmRequest request);
}
