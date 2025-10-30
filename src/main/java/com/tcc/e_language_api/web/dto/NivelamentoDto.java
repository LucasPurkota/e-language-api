package com.tcc.e_language_api.web.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class NivelamentoDto {
    public UUID nivelamentoId;
    public UUID perfilIdiomaId;
    public int nivelIdiomaId;
    public Double nota;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    public LocalDateTime dataRealizacao;
    public int statusId;
    public List<NivelamentoQuestaoAulaDto> questoes;
}
