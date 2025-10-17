package com.tcc.e_language_api.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AvaliacaoDto {
    private UUID avaliacaoId;
    private UUID alunoUnidadeId;
    private String nota;
    private LocalDateTime dataRealizacao;
}
