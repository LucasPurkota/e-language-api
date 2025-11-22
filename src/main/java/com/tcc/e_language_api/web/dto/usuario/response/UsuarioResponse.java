package com.tcc.e_language_api.web.dto.usuario.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcc.e_language_api.web.dto.endereco.response.EnderecoResponse;
import com.tcc.e_language_api.web.dto.perfil.response.PerfilResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados completos de um usuário")
public class UsuarioResponse {
    
    @Schema(description = "ID único do usuário", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID usuarioId;
    
    @Schema(description = "Nome completo do usuário", example = "João Silva")
    private String nome;
    
    @Schema(description = "CPF do usuário", example = "123.456.789-00")
    private String cpf;
    
    @Schema(description = "Email do usuário", example = "joao.silva@email.com")
    private String email;
    
    @Schema(description = "Data de criação do usuário")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate criadoEm;
    
    @Schema(description = "Data da última atualização")
    private LocalDateTime atualizadoEm;
    
    @Schema(description = "Lista de endereços do usuário")
    private List<EnderecoResponse> enderecos;
    
    @Schema(description = "Lista de perfis do usuário")
    private List<PerfilResponse> perfil;
}