package com.tcc.e_language_api.web.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AulaDto {
    private UUID aulaId;
    private UUID unidadeId;
    private int numero;
    private String titulo;
    private String conteudo;
    private String linkVideo;
}
