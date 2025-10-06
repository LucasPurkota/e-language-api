package com.tcc.e_language_api.web.dto.endereco.request;

import com.tcc.e_language_api.entity.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para criação de um endereço")
public class EnderecoCreateRequest {
    
    @NotNull(message = "Tipo do endereço é obrigatório")
    @Schema(description = "Tipo do endereço", example = "RESIDENCIAL")
    private Endereco.Tipo tipo;
    
    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato 00000-000")
    @Schema(description = "CEP do endereço", example = "01234-567")
    private String cep;
    
    @NotBlank(message = "Logradouro é obrigatório")
    @Schema(description = "Nome da rua/avenida", example = "Rua das Flores")
    private String logradouro;
    
    @NotBlank(message = "Bairro é obrigatório")
    @Schema(description = "Nome do bairro", example = "Centro")
    private String bairro;
    
    @NotBlank(message = "Cidade é obrigatória")
    @Schema(description = "Nome da cidade", example = "São Paulo")
    private String cidade;
    
    @NotBlank(message = "UF é obrigatório")
    @Pattern(regexp = "[A-Z]{2}", message = "UF deve ter 2 letras maiúsculas")
    @Schema(description = "Unidade Federativa", example = "SP")
    private String uf;
    
    @NotBlank(message = "País é obrigatório")
    @Schema(description = "Nome do país", example = "Brasil")
    private String pais;
    
    @Schema(description = "Complemento do endereço", example = "Apto 123")
    private String complemento;
    
    @NotNull(message = "Número é obrigatório")
    @Positive(message = "Número deve ser positivo")
    @Schema(description = "Número do endereço", example = "123")
    private Integer numero;
}