package com.tcc.e_language_api.web.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class NivelamentoRespostaDto {
    private UUID nivelamentoQuestaoAulaId;
    private String gabarito;
    private String resposta;
}
