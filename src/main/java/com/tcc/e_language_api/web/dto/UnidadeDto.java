package com.tcc.e_language_api.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UnidadeDto {
    private UUID unidadeId;
    private UUID idiomaId;
    private int nivelIdiomaId;
    private int numero;
    private String titulo;
    private String conteudo;
    private int StatusId;
    private String status;
}
