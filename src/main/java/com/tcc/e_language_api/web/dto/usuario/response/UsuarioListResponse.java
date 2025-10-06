package com.tcc.e_language_api.web.dto.usuario.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados resumidos de um usuário para listagem")
public class UsuarioListResponse {
    
    @Schema(description = "ID único do usuário", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID usuarioId;
    
    @Schema(description = "Nome completo do usuário", example = "João Silva")
    private String nome;
    
    @Schema(description = "Email do usuário", example = "joao.silva@email.com")
    private String email;
    
    @Schema(description = "Data de criação do usuário")
    private LocalDateTime criadoEm;
    
    @Schema(description = "Tipo de perfil principal", example = "ALUNO")
    private String perfilPrincipal;
    
    @Schema(description = "Status do usuário", example = "ATIVO")
    private String status;
}