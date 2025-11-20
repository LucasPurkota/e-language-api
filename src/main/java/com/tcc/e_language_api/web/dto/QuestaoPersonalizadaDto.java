package com.tcc.e_language_api.web.dto;

import com.tcc.e_language_api.entity.AlternativaQuestaoPersonalizada;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class QuestaoPersonalizadaDto {
    private UUID questaoPersonalizadaId;
    private UUID idiomaId;
    private UUID alunoProfessorId;
    private int nivelDificuldadeId;
    private String enunciado;
    private int tipoQuestaoId;
    private String gabarito;
    private String correto;
    private int statusId;
    private String status;
    private List<AlternativaQuestaoPersonalizadaDto> alternativas;
}
