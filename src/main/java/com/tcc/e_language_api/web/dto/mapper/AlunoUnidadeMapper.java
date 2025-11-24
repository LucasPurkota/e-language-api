package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.AlunoUnidade;
import com.tcc.e_language_api.entity.AlunoUnidadeAula;
import com.tcc.e_language_api.web.dto.AlunoUnidadeAulaDto;
import com.tcc.e_language_api.web.dto.AlunoUnidadeDto;
import com.tcc.e_language_api.web.dto.UnidadeDto;

import java.util.List;
import java.util.stream.Collectors;

public class AlunoUnidadeMapper {
    public static AlunoUnidadeDto toDto(AlunoUnidade alunoUnidade){
        AlunoUnidadeDto dto = new AlunoUnidadeDto();
        dto.setAlunoUnidadeId(alunoUnidade.getAlunoUnidadeId());
        dto.setAlunoId(alunoUnidade.getAluno().getPerfilId());
        dto.setStatusId(alunoUnidade.getStatus().getStatusId());
        dto.setStatusDescricao(alunoUnidade.getStatus().getDescricao());
        dto.setUnidade(UnidadeMapper.toDto(alunoUnidade.getUnidade()));
        return dto;
    }

    public static List<AlunoUnidadeDto> toListDto(List<AlunoUnidade> unidades) {
        return unidades.stream().map(unidade -> toDto(unidade)).collect(Collectors.toList());
    }
}
