package com.tcc.e_language_api.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AvaliacaoSatisfacaoProfessorDto {
    private UUID avaliacaoSatisfacaoProfessorId;
    private UUID alunoProfessorId;
    private String avaliacao;
    private int pontos;
}
