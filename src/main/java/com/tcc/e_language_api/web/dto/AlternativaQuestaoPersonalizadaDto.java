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
public class AlternativaQuestaoPersonalizadaDto {
    private UUID alternativaQuestaoPersonalizadaId;
    private UUID questaoPersonalizadaId;
    private String alternativa;
    private String descricao;
}
