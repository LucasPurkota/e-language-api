package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Aula;
import com.tcc.e_language_api.entity.Nivelamento;
import com.tcc.e_language_api.entity.NivelamentoQuestaoAula;
import com.tcc.e_language_api.entity.Unidade;
import com.tcc.e_language_api.web.dto.AulaDto;
import com.tcc.e_language_api.web.dto.NivelamentoDto;
import com.tcc.e_language_api.web.dto.NivelamentoQuestaoAulaDto;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class NivelamentoMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static NivelamentoDto toDto(Nivelamento nivelamento) {
        NivelamentoDto dto = new NivelamentoDto();

        if (nivelamento.getNivelIdioma() != null) {
            dto.setNivelIdiomaId(nivelamento.getNivelIdioma().getNivelIdiomaId());
        }
        dto.setNivelamentoId(nivelamento.getNivelamentoId());
        dto.setDataRealizacao(nivelamento.getDataRealizacao());
        dto.setNota(nivelamento.getNota());
        dto.setPerfilIdiomaId(nivelamento.getPerfilIdioma().getPerfilIdiomaId());
        dto.setStatusId(nivelamento.getStatus().getStatusId());

        List<NivelamentoQuestaoAulaDto> questoes = new ArrayList<>();
        for(NivelamentoQuestaoAula questao : nivelamento.getNivelamentoQuestaoAulas()) {
            NivelamentoQuestaoAulaDto qDto = new NivelamentoQuestaoAulaDto();
            qDto.setNivelamentoQuestaoAulaId(questao.getNivelamentoQuestaoAulaid());
            qDto.setNivelamentoId(questao.getNivelamento().getNivelamentoId());
            qDto.setQuestaoAulaId(questao.getQuestaoAula().getQuestaoAulaId());
            qDto.setResposta(questao.getResposta());
            qDto.setQuestao(QuestaoAulaMapper.toDto(questao.getQuestaoAula()));
            questoes.add(qDto);
        }

        dto.setQuestoes(questoes);

        return dto;
    }
}
