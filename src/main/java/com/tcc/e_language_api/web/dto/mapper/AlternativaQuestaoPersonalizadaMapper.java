package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.AlternativaQuestaoAula;
import com.tcc.e_language_api.entity.AlternativaQuestaoPersonalizada;
import com.tcc.e_language_api.entity.QuestaoPersonalizada;
import com.tcc.e_language_api.web.dto.AlternativaQuestaoAulaDto;
import com.tcc.e_language_api.web.dto.AlternativaQuestaoPersonalizadaDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AlternativaQuestaoPersonalizadaMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static AlternativaQuestaoPersonalizada toEntity(AlternativaQuestaoPersonalizadaDto dto) {

        QuestaoPersonalizada questaoPersonalizada = new QuestaoPersonalizada();
        questaoPersonalizada.setQuestaoPersonalizadaId(dto.getQuestaoPersonalizadaId());

        AlternativaQuestaoPersonalizada alternativaQuestaoPersonalizada = new AlternativaQuestaoPersonalizada();
        alternativaQuestaoPersonalizada.setQuestaoPersonalizada(questaoPersonalizada);
        alternativaQuestaoPersonalizada.setAlternativa(dto.getAlternativa());
        alternativaQuestaoPersonalizada.setDescricao(dto.getDescricao());
        return alternativaQuestaoPersonalizada;
    }

    public static AlternativaQuestaoPersonalizadaDto toDto(AlternativaQuestaoPersonalizada alternativaQuestaoPersonalizada) {

        AlternativaQuestaoPersonalizadaDto dto = new AlternativaQuestaoPersonalizadaDto();
        dto.setAlternativaQuestaoPersonalizadaId(alternativaQuestaoPersonalizada.getAlternativaQuestaoPersonalizadaId());
        dto.setQuestaoPersonalizadaId(alternativaQuestaoPersonalizada.getQuestaoPersonalizada().getQuestaoPersonalizadaId());
        dto.setAlternativa(alternativaQuestaoPersonalizada.getAlternativa());
        dto.setDescricao(alternativaQuestaoPersonalizada.getDescricao());

        return dto;
    }

    public static List<AlternativaQuestaoPersonalizadaDto> toListDto(List<AlternativaQuestaoPersonalizada> alternativaQuestaoAula) {
        return alternativaQuestaoAula.stream().map(alternativa -> toDto(alternativa)).collect(Collectors.toList());
    }
}
