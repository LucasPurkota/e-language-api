package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.AlunoProfessor;
import com.tcc.e_language_api.entity.AvaliacaoSatisfacaoProfessor;
import com.tcc.e_language_api.web.dto.AlunoProfessorDto;
import com.tcc.e_language_api.web.dto.AvaliacaoSatisfacaoProfessorDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AvaliacaoSatisfacaoProfessorMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static AvaliacaoSatisfacaoProfessor toEntity(AvaliacaoSatisfacaoProfessorDto dto) {
        AvaliacaoSatisfacaoProfessor entity = modelMapper.map(dto, AvaliacaoSatisfacaoProfessor.class);

        AlunoProfessor alunoProfessor = new AlunoProfessor();
        alunoProfessor.setAlunoProfessorId(dto.getAlunoProfessorId());

        entity.setAlunoProfessor(alunoProfessor);
        return entity;
    }

    public static AvaliacaoSatisfacaoProfessorDto toDto(AvaliacaoSatisfacaoProfessor avaliacaoSatisfacaoProfessor){
        AvaliacaoSatisfacaoProfessorDto dto = new AvaliacaoSatisfacaoProfessorDto();
        dto.setAvaliacaoSatisfacaoProfessorId(avaliacaoSatisfacaoProfessor.getAvaliacaoSatisfacaoProfessorId());
        dto.setAlunoProfessorId(avaliacaoSatisfacaoProfessor.getAlunoProfessor().getAlunoProfessorId());
        dto.setAvaliacao(avaliacaoSatisfacaoProfessor.getAvaliacao());
        dto.setPontos(avaliacaoSatisfacaoProfessor.getPontos());
        return dto;
    }

    public static List<AvaliacaoSatisfacaoProfessorDto> toListDto(List<AvaliacaoSatisfacaoProfessor> avaliacaoSatisfacaoProfessor) {
        return avaliacaoSatisfacaoProfessor.stream().map(avaliacaoSatisfacao -> toDto(avaliacaoSatisfacao))
                .collect(Collectors.toList());
    }
}
