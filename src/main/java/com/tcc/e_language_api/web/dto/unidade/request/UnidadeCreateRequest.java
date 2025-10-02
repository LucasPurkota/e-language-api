package com.tcc.e_language_api.web.dto.unidade.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para criação de uma nova unidade")
public class UnidadeCreateRequest {
    
    @NotNull(message = "ID do idioma é obrigatório")
    @Schema(description = "ID do idioma", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID idiomaId;
    
    @NotNull(message = "ID do nível do idioma é obrigatório")
    @Schema(description = "ID do nível do idioma", example = "1")
    private Integer nivelIdiomaId;
    
    @NotNull(message = "Número da unidade é obrigatório")
    @Positive(message = "Número da unidade deve ser positivo")
    @Schema(description = "Número da unidade", example = "1")
    private Integer numero;
    
    @NotBlank(message = "Título da unidade é obrigatório")
    @Size(min = 3, max = 100, message = "Título deve ter entre 3 e 100 caracteres")
    @Schema(description = "Título da unidade", example = "Introdução ao Inglês")
    private String titulo;
    
    @NotBlank(message = "Conteúdo da unidade é obrigatório")
    @Size(min = 10, max = 5000, message = "Conteúdo deve ter entre 10 e 5000 caracteres")
    @Schema(description = "Conteúdo da unidade", example = "Nesta unidade você aprenderá os cumprimentos básicos em inglês...")
    private String conteudo;
}