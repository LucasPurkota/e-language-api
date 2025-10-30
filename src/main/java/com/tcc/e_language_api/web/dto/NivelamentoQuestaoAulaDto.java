package com.tcc.e_language_api.web.dto;

import com.tcc.e_language_api.entity.QuestaoAula;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class NivelamentoQuestaoAulaDto {
    public UUID nivelamentoQuestaoAulaId;
    public UUID nivelamentoId;
    public UUID questaoAulaId;
    public QuestaoAulaDto questao;
    public String resposta;
}
