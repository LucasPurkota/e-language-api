package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.AlunoProfessor;
import com.tcc.e_language_api.entity.AlunoUnidadeAula;
import com.tcc.e_language_api.web.dto.AlunoProfessorDto;
import com.tcc.e_language_api.web.dto.AlunoUnidadeAulaDto;

import java.util.List;
import java.util.stream.Collectors;

public class AlunoUnidadeAulaMapper {
    public static AlunoUnidadeAulaDto toDto(AlunoUnidadeAula alunoUnidadeAula){
        AlunoUnidadeAulaDto dto = new AlunoUnidadeAulaDto();
        dto.setAlunoUnidadeAulaId(alunoUnidadeAula.getAlunoUnidadeAulaId());
        dto.setAlunoUnidadeId(alunoUnidadeAula.getAlunoUnidade().getAlunoUnidadeId());
        dto.setStatusId(alunoUnidadeAula.getStatus().getStatusId());
        dto.setStatusDescricao(alunoUnidadeAula.getStatus().getDescricao());
        dto.setAula(AulaMapper.toDto(alunoUnidadeAula.getAula()));
        return dto;
    }

    public static List<AlunoUnidadeAulaDto> toListDto(List<AlunoUnidadeAula> aulas) {
        return aulas.stream().map(aula -> toDto(aula)).collect(Collectors.toList());
    }
}
