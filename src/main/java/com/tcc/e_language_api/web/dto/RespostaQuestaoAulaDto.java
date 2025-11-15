package com.tcc.e_language_api.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RespostaQuestaoAulaDto {
    private UUID questaoAulaId;
    private UUID perfilId;
    private UUID unidadeId;
    private String gabarito;
    private String resposta;
}
