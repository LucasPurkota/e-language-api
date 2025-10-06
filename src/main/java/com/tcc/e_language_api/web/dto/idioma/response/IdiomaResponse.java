package com.tcc.e_language_api.web.dto.idioma.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados de um idioma")
public class IdiomaResponse {
    
    @Schema(description = "ID único do idioma", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID idiomaId;
    
    @Schema(description = "Nome do idioma", example = "Inglês")
    private String nome;
    
    @Schema(description = "Sigla do idioma", example = "EN")
    private String sigla;
}