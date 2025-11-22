package com.tcc.e_language_api.web.dto.perfil.response;

import com.tcc.e_language_api.web.dto.PerfilIdiomaDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados de um perfil de usuário")
public class PerfilResponse {
    
    @Schema(description = "ID único do perfil", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID perfilId;

    @Schema(description = "Nome do usuario", example = "Lucas")
    private String nome;

    @Schema(description = "ID do tipo de perfil", example = "1")
    private int tipoPerfilId;

    @Schema(description = "Descrição do tipo de perfil", example = "ALUNO")
    private String tipoPerfilDescricao;

    @Schema(description = "Posicao do ranking", example = "1")
    private int posicaoRanking;
}