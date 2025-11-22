package com.tcc.e_language_api.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AvaliacaoDto {
    private UUID avaliacaoId;
    private UUID alunoUnidadeId;
    private Double nota;
    private LocalDateTime dataRealizacao;
    private String aprovado;
    private String statusDescricao;
    private int statusId;
    private List<AvaliacaoQuestaoAulaDto> questoes;
}
