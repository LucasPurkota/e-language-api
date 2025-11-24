package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Chat;
import com.tcc.e_language_api.entity.DesafioDiario;
import com.tcc.e_language_api.web.dto.ChatDto;
import com.tcc.e_language_api.web.dto.DesafioDiarioDto;

import java.util.List;
import java.util.stream.Collectors;

public class DesafioDiarioMapper {
    public static DesafioDiarioDto toDto(DesafioDiario desafioDiario) {
        DesafioDiarioDto dto = new DesafioDiarioDto();
        dto.setDesafioDiarioId(desafioDiario.getDesafioDiarioId());
        dto.setPerfilIdiomaId(desafioDiario.getPerfilIdioma().getPerfilIdiomaId());
        dto.setStatusId(desafioDiario.getStatus().getStatusId());
        dto.setStatusDescricao(desafioDiario.getStatus().getDescricao());
        dto.setCorreto((desafioDiario.getCorreto() != null && desafioDiario.getCorreto().equals("S")) ? "Sim":"Não");
        dto.setResposta(desafioDiario.getResposta());
        dto.setQuestao(QuestaoAulaMapper.toDto(desafioDiario.getQuestaoAula()));
        return dto;
    }

    public static List<DesafioDiarioDto> toListDto(List<DesafioDiario> desafios){
        return desafios.stream().map(desafio -> toDto(desafio)).collect(Collectors.toList());
    }
}
