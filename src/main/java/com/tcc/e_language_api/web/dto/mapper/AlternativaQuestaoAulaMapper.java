package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.*;
import com.tcc.e_language_api.web.dto.AlternativaQuestaoAulaDto;
import com.tcc.e_language_api.web.dto.QuestaoAulaDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AlternativaQuestaoAulaMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static AlternativaQuestaoAula toEntity(AlternativaQuestaoAulaDto dto) {
        AlternativaQuestaoAula alternativaQuestaoAula = modelMapper.map(dto, AlternativaQuestaoAula.class);

        QuestaoAula questaoAula = new QuestaoAula();
        questaoAula.setQuestaoAulaId(dto.getQuestaoAulaId());

        alternativaQuestaoAula.setQuestaoAula(questaoAula);

        return alternativaQuestaoAula;
    }

    public static  AlternativaQuestaoAulaDto toDto(AlternativaQuestaoAula alternativaQuestaoAula) {
//        AlternativaQuestaoAulaDto dto = modelMapper.map(alternativaQuestaoAula, AlternativaQuestaoAulaDto.class);

        AlternativaQuestaoAulaDto dto = new AlternativaQuestaoAulaDto();
        dto.setAlternativaQuestaoAulaId(alternativaQuestaoAula.getAlternativaQuestaoAulaId());
        dto.setQuestaoAulaId(alternativaQuestaoAula.getQuestaoAula().getQuestaoAulaId());
        dto.setAlternativa(alternativaQuestaoAula.getAlternativa());
        dto.setDescricao(alternativaQuestaoAula.getDescricao());

        return dto;
    }

    public static List<AlternativaQuestaoAulaDto> toListDto(List<AlternativaQuestaoAula> alternativaQuestaoAula) {
        return alternativaQuestaoAula.stream().map(alternativa -> toDto(alternativa)).collect(Collectors.toList());
    }
}
