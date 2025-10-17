package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.AlunoProfessor;
import com.tcc.e_language_api.entity.AvaliacaoSatisfacaoProfessor;
import com.tcc.e_language_api.web.dto.AvaliacaoSatisfacaoProfessorDto;
import org.modelmapper.ModelMapper;

public class AvaliacaoSatisfacaoProfessorMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static AvaliacaoSatisfacaoProfessor toEntity(AvaliacaoSatisfacaoProfessorDto dto) {
        AvaliacaoSatisfacaoProfessor entity = modelMapper.map(dto, AvaliacaoSatisfacaoProfessor.class);

        AlunoProfessor alunoProfessor = new AlunoProfessor();
        alunoProfessor.setAlunoProfessorId(dto.getAlunoProfessorId());

        entity.setAlunoProfessor(alunoProfessor);
        return entity;
    }
}
