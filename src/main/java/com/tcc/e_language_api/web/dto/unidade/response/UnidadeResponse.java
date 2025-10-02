package com.tcc.e_language_api.web.dto.unidade.response;

import com.tcc.e_language_api.web.dto.idioma.response.IdiomaResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados de uma unidade")
public class UnidadeResponse {
    
    @Schema(description = "ID único da unidade", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID unidadeId;
    
    @Schema(description = "ID do idioma", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID idiomaId;
    
    @Schema(description = "ID do nível do idioma", example = "1")
    private Integer nivelIdiomaId;
    
    @Schema(description = "Número da unidade", example = "1")
    private Integer numero;
    
    @Schema(description = "Título da unidade", example = "Introdução ao Inglês")
    private String titulo;
    
    @Schema(description = "Conteúdo da unidade", example = "Nesta unidade você aprenderá os cumprimentos básicos em inglês...")
    private String conteudo;
    
    @Schema(description = "Dados do idioma associado")
    private IdiomaResponse idioma;
    
    @Schema(description = "Descrição do nível", example = "BÁSICO")
    private String nivelDescricao;
}