package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.*;
import com.tcc.e_language_api.web.dto.QuestaoAulaDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class QuestaoAulaMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static QuestaoAula toEntity(QuestaoAulaDto questaoAulaDto) {
        QuestaoAula questaoAula = modelMapper.map(questaoAulaDto, QuestaoAula.class);

        Aula aula = new Aula();
        aula.setAulaId(questaoAulaDto.getAulaId());

        NivelDificuldade nivelDificuldade = new NivelDificuldade();
        nivelDificuldade.setNivelDificuldadeId(questaoAulaDto.getNivelDificuldadeId());

        TipoQuestao tipoQuestao = new TipoQuestao();
        tipoQuestao.setTipoQuestaoId(questaoAulaDto.getTipoQuestaoId());

        questaoAula.setAula(aula);
        questaoAula.setNivelDificuldade(nivelDificuldade);
        questaoAula.setTipoQuestao(tipoQuestao);

        return questaoAula;
    }

    public static  QuestaoAulaDto toDto(QuestaoAula questaoAula) {
        QuestaoAulaDto dto = modelMapper.map(questaoAula, QuestaoAulaDto.class);

        dto.setNivelDificuldadeId(questaoAula.getNivelDificuldade().getNivelDificuldadeId());
        dto.setTipoQuestaoId(questaoAula.getTipoQuestao().getTipoQuestaoId());

        return dto;
    }

    public static List<QuestaoAulaDto> toListDto(List<QuestaoAula> questaoAula) {
        return questaoAula.stream().map(questao -> toDto(questao)).collect(Collectors.toList());
    }
}
