package com.tcc.e_language_api.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PlanoDto {
    private UUID planoId;
    private String titulo;
    private String descricao;
    private double valor;
}
