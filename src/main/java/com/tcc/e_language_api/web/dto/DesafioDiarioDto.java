package com.tcc.e_language_api.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class DesafioDiarioDto {
    private UUID desafioDiarioId;
    private UUID perfilIdiomaId;
    private int statusId;
    private String statusDescricao;
    private String resposta;
    private String correto;
    private QuestaoAulaDto questao;
}
