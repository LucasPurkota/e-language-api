package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Idioma;
import com.tcc.e_language_api.web.dto.IdiomaDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class IdiomaMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static Idioma toEntity(IdiomaDto idiomaDto) {
        return modelMapper.map(idiomaDto, Idioma.class);
    }

    public static IdiomaDto toDto(Idioma idioma) {
        return modelMapper.map(idioma, IdiomaDto.class);
    }

    public static List<IdiomaDto> toListDto(List<Idioma> idiomas) {
        return idiomas.stream()
                .map(idioma -> toDto(idioma))
                .collect(Collectors.toList());
    }
}
