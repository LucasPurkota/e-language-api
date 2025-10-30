package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Aula;
import com.tcc.e_language_api.entity.Idioma;
import com.tcc.e_language_api.entity.Unidade;
import com.tcc.e_language_api.web.dto.AulaDto;
import com.tcc.e_language_api.web.dto.IdiomaDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AulaMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static Aula toEntity(AulaDto aulaDto) {
        Aula aula = modelMapper.map(aulaDto, Aula.class);
        Unidade unidade = new Unidade();
        unidade.setUnidadeId(aulaDto.getUnidadeId());
        aula.setUnidade(unidade);
        return aula;
    }

    public static AulaDto toDto(Aula aula) {
        AulaDto dto = modelMapper.map(aula, AulaDto.class);
        if(aula.getUnidade() != null){
            dto.setUnidadeId(aula.getUnidade().getUnidadeId());
        }
        return dto;
    }

    public static List<AulaDto> toListDto(List<Aula> aulas) {
        return aulas.stream()
                .map(aula -> toDto(aula))
                .collect(Collectors.toList());
    }
}
