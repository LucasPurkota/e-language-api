package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.QuestaoPersonalizada;
import com.tcc.e_language_api.entity.RespostaQuestaoAula;
import com.tcc.e_language_api.web.dto.RespostaQuestaoAulaResponseDto;
import com.tcc.e_language_api.web.dto.RespostaQuestaoPersonalizadaDto;
import com.tcc.e_language_api.web.dto.RespostaQuestaoPersonalizadaResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class RespostaQuestaoPersonalizadaMapper {
    public static RespostaQuestaoPersonalizadaResponseDto toDto(QuestaoPersonalizada questaoPersonalizada) {
        RespostaQuestaoPersonalizadaResponseDto dto = new RespostaQuestaoPersonalizadaResponseDto();
        dto.setQuestaoPersonalizadaId(questaoPersonalizada.getQuestaoPersonalizadaId());
        if (questaoPersonalizada.getCorreto().equals("S")) {
            dto.setCorreto("Sim");
        }else {
            dto.setCorreto("Não");
        }
        return dto;
    }

    public static List<RespostaQuestaoPersonalizadaResponseDto> toListDto(List<QuestaoPersonalizada> entities) {
        return entities.stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }
}
