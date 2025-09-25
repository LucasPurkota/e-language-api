package com.tcc.e_language_api.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class QuestaoAulaDto {
    private UUID questaoAulaId;
    private UUID aulaId;
    private int nivelDificuldadeId;
    private String enunciado;
    private int tipoQuestaoId;
    private String gabarito;
}
