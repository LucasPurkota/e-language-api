package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.NivelIdioma;
import com.tcc.e_language_api.entity.Unidade;
import com.tcc.e_language_api.web.dto.UnidadeDto;
import org.modelmapper.ModelMapper;

public class UnidadeMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static Unidade toEntity(UnidadeDto unidadeDto) {
        Unidade unidade = modelMapper.map(unidadeDto, Unidade.class);
        NivelIdioma nivelIdioma = new NivelIdioma();
        nivelIdioma.setNivelIdiomaId(unidadeDto.getNivelIdiomaId());
        unidade.setNivelIdioma(nivelIdioma);
        return unidade;
    }
}
