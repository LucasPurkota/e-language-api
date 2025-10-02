package com.tcc.e_language_api.web.dto.endereco.response;

import com.tcc.e_language_api.entity.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados de um endereço")
public class EnderecoResponse {
    
    @Schema(description = "ID único do endereço", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID enderecoId;
    
    @Schema(description = "Tipo do endereço", example = "RESIDENCIAL")
    private Endereco.Tipo tipo;
    
    @Schema(description = "CEP do endereço", example = "01234-567")
    private String cep;
    
    @Schema(description = "Nome da rua/avenida", example = "Rua das Flores")
    private String logradouro;
    
    @Schema(description = "Nome do bairro", example = "Centro")
    private String bairro;
    
    @Schema(description = "Nome da cidade", example = "São Paulo")
    private String cidade;
    
    @Schema(description = "Unidade Federativa", example = "SP")
    private String uf;
    
    @Schema(description = "Nome do país", example = "Brasil")
    private String pais;
    
    @Schema(description = "Complemento do endereço", example = "Apto 123")
    private String complemento;
    
    @Schema(description = "Número do endereço", example = "123")
    private Integer numero;
}