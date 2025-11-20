package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.NivelamentoQuestaoAula;
import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.entity.RespostaQuestaoAula;
import com.tcc.e_language_api.web.dto.RankingDto;
import com.tcc.e_language_api.web.dto.RespostaQuestaoAulaDto;
import com.tcc.e_language_api.web.dto.RespostaQuestaoAulaResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class RespostaQuestaoAulaMapper {
    public static RespostaQuestaoAulaResponseDto toDto(RespostaQuestaoAula respostaQuestaoAula) {
        RespostaQuestaoAulaResponseDto dto = new RespostaQuestaoAulaResponseDto();
        dto.setQuestaoAulaId(respostaQuestaoAula.getQuestaoAula().getQuestaoAulaId());
        if (respostaQuestaoAula.getCorreto().equals("S")) {
            dto.setCorreto("Sim");
        }else {
            dto.setCorreto("Não");
        }
        return dto;
    }

    public static List<RespostaQuestaoAulaResponseDto> toListDto(List<RespostaQuestaoAula> entities) {
        return entities.stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }



    public static RespostaQuestaoAulaResponseDto nivelamentoToDto(NivelamentoQuestaoAula resposta) {
        RespostaQuestaoAulaResponseDto dto = new RespostaQuestaoAulaResponseDto();
        dto.setQuestaoAulaId(resposta.getQuestaoAula().getQuestaoAulaId());
        if (resposta.getCorreto().equals("S")) {
            dto.setCorreto("Sim");
        }else {
            dto.setCorreto("Não");
        }
        return dto;
    }

    public static List<RespostaQuestaoAulaResponseDto> nivelamentoToListDto(List<NivelamentoQuestaoAula> entities) {
        return entities.stream()
                .map(entity -> nivelamentoToDto(entity))
                .collect(Collectors.toList());
    }
}
