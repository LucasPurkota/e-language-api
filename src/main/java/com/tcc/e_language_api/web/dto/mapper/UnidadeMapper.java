package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Idioma;
import com.tcc.e_language_api.entity.NivelIdioma;
import com.tcc.e_language_api.entity.Unidade;
import com.tcc.e_language_api.web.dto.UnidadeDto;
import org.modelmapper.ModelMapper;

public class UnidadeMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static Unidade toEntity(UnidadeDto unidadeDto) {
        Unidade unidade = modelMapper.map(unidadeDto, Unidade.class);
        Idioma idioma = new Idioma();
        idioma.setIdiomaId(unidadeDto.getIdiomaId());
        NivelIdioma nivelIdioma = new NivelIdioma();
        nivelIdioma.setNivelIdiomaId(unidadeDto.getNivelIdiomaId());
        unidade.setIdioma(idioma);
        unidade.setNivelIdioma(nivelIdioma);
        return unidade;
    }

    public static UnidadeDto toDto(Unidade unidade) {
        UnidadeDto dto = modelMapper.map(unidade, UnidadeDto.class);
        if (unidade.getIdioma() != null) {
            dto.setIdiomaId(unidade.getIdioma().getIdiomaId());
        }
        if (unidade.getNivelIdioma() != null) {
            dto.setNivelIdiomaId(unidade.getNivelIdioma().getNivelIdiomaId());
        }
        return dto;
    }
}
