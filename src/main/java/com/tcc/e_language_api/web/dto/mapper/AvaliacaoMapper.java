package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Avaliacao;
import com.tcc.e_language_api.entity.AvaliacaoQuestaoAula;
import com.tcc.e_language_api.web.dto.AvaliacaoQuestaoAulaDto;
import com.tcc.e_language_api.web.dto.AvaliacaoDto;
import com.tcc.e_language_api.web.dto.RespostaQuestaoAulaResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AvaliacaoMapper {
    public static AvaliacaoDto toDto(Avaliacao avaliacao) {
        AvaliacaoDto dto = new AvaliacaoDto();
        dto.setAvaliacaoId(avaliacao.getAvaliacaoId());
        dto.setNota(avaliacao.getNota());
        dto.setAlunoUnidadeId(avaliacao.getAlunoUnidade().getAlunoUnidadeId());
        dto.setDataRealizacao(avaliacao.getDataRealizacao());
        dto.setAprovado((avaliacao.getAprovado() != null && avaliacao.getAprovado().equals("S")) ? "Sim":"Não");
        dto.setStatusId(avaliacao.getStatus().getStatusId());
        dto.setStatusDescricao(avaliacao.getStatus().getDescricao());

        List<AvaliacaoQuestaoAulaDto> questoesDto = new ArrayList<>();
        for (AvaliacaoQuestaoAula questao : avaliacao.getAvaliacaoQuestaoAula()) {
            AvaliacaoQuestaoAulaDto questaoDto = new AvaliacaoQuestaoAulaDto();

            questaoDto.setQuestaoAula(QuestaoAulaMapper.toDto(questao.getQuestaoAula()));
            questaoDto.setAvaliacaoQuestaoAulaId(questao.getAvaliacaoQuestaoAulaId());
            questaoDto.setResposta(questao.getResposta());
            questaoDto.setCorreto((questao.getCorreto() != null && questao.getCorreto().equals("S")) ? "Sim":"Não");
            questoesDto.add(questaoDto);
        }

        dto.setQuestoes(questoesDto);

        return dto;
    }

    public static List<AvaliacaoDto> toListDto(List<Avaliacao> avaliacoes){
        return avaliacoes.stream().map(avaliacao -> toDto(avaliacao)).collect(Collectors.toList());
    }


    public static RespostaQuestaoAulaResponseDto toDtoResposta(AvaliacaoQuestaoAula questao) {
        RespostaQuestaoAulaResponseDto dto = new RespostaQuestaoAulaResponseDto();
        dto.setQuestaoAulaId(questao.getQuestaoAula().getQuestaoAulaId());
        dto.setCorreto((questao.getCorreto()) != null && questao.getCorreto().equals("S") ? "Sim":"Não");
        return dto;
    }

    public static List<RespostaQuestaoAulaResponseDto> toListDtoResposta(List<AvaliacaoQuestaoAula> questoes){
        return questoes.stream().map(questao -> toDtoResposta(questao)).collect(Collectors.toList());
    }


}
