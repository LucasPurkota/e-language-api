package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Aula;
import com.tcc.e_language_api.entity.Unidade;
import com.tcc.e_language_api.web.dto.AulaDto;
import org.modelmapper.ModelMapper;

public class AulaMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static Aula toEntity(AulaDto aulaDto) {
        Aula aula = modelMapper.map(aulaDto, Aula.class);
        Unidade unidade = new Unidade();
        unidade.setUnidadeId(aulaDto.getUnidadeId());
        aula.setUnidade(unidade);
        return aula;
    }
}
