package com.tcc.e_language_api.web.controller;

import com.tcc.e_language_api.jwt.JwtToken;
import com.tcc.e_language_api.jwt.JwtUserDetailsService;
import com.tcc.e_language_api.web.dto.UsuarioLoginDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


import org.springframework.security.core.AuthenticationException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Tag(name = "2. Autenticação", description = "Operações de autenticação e autorização")
public class AutenticacaoController {
    private final JwtUserDetailsService detailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    @Operation(summary = "Autenticar usuário", 
               description = "Realiza a autenticação do usuário e retorna um token JWT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso", 
                    content = @Content(schema = @Schema(implementation = JwtToken.class))),
        @ApiResponse(responseCode = "400", description = "Credenciais inválidas"),
        @ApiResponse(responseCode = "422", description = "Dados de entrada inválidos")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Dados de login do usuário",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = UsuarioLoginDto.class),
            examples = {
                @io.swagger.v3.oas.annotations.media.ExampleObject(
                    name = "Login Válido",
                    summary = "Exemplo de login com credenciais corretas",
                    description = "Login com email e senha válidos",
                    value = "{ \"email\": \"maria.santos@email.com\", \"senha\": \"SenhaForte@456\" }"
                )
            }
        )
    )
    public ResponseEntity<?> autenticar(@RequestBody @Valid UsuarioLoginDto dto, HttpServletRequest request) {
        log.info("Processo de autenticação  pelo login {}", dto.getEmail());
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());

            authenticationManager.authenticate(authenticationToken);

            JwtToken token = detailsService.getTokenAuthenticated(dto.getEmail());

            return ResponseEntity.ok(token);
        } catch (AuthenticationException ex) {
            log.warn("Bad Credentials from username '{}'", dto.getEmail());
        }
        return ResponseEntity
                .badRequest()
                .body( "Credenciais Inválidas");
    }

}
