package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Idioma;
import com.tcc.e_language_api.web.dto.IdiomaDto;
import org.modelmapper.ModelMapper;

public class IdiomaMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static Idioma toEntity(IdiomaDto idiomaDto) {
        return modelMapper.map(idiomaDto, Idioma.class);
    }
}
