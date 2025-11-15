package com.tcc.e_language_api.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RespostaQuestaoAulaResponseDto {
    private UUID questaoAulaId;
    private String correto;
}
