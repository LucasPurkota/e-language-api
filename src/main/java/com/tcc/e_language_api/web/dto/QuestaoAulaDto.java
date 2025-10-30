package com.tcc.e_language_api.web.dto;

import com.tcc.e_language_api.entity.AlternativaQuestaoAula;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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
    private List<AlternativaQuestaoAulaDto> alternativas;
}
