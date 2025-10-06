package com.tcc.e_language_api.web.dto.idioma.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para criação de um novo idioma")
public class IdiomaCreateRequest {
    
    @NotBlank(message = "Nome do idioma é obrigatório")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
    @Schema(description = "Nome do idioma", example = "Inglês")
    private String nome;
    
    @NotBlank(message = "Sigla do idioma é obrigatória")
    @Pattern(regexp = "[A-Z]{2,3}", message = "Sigla deve ter 2 ou 3 letras maiúsculas")
    @Schema(description = "Sigla do idioma", example = "EN")
    private String sigla;
}