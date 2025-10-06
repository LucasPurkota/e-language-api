package com.tcc.e_language_api.web.dto;

import com.tcc.e_language_api.entity.AlternativaQuestaoAula;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlternativaQuestaoAulaDto {
    private UUID alternativaQuestaoAulaId;
    private UUID questaoAulaId;
    private String alternativa;
    private String descricao;
}
