package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.*;
import com.tcc.e_language_api.web.dto.AvaliacaoSatisfacaoProfessorDto;
import com.tcc.e_language_api.web.dto.PerfilIdiomaDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PerfilIdiomaMapper {

    public static PerfilIdioma toEntity(PerfilIdiomaDto dto) {
        Perfil perfil = new Perfil();
        perfil.setPerfilId(dto.getPerfilId());

        Idioma idioma = new Idioma();
        idioma.setIdiomaId(dto.getIdiomaId());

        NivelIdioma nivelIdioma = new NivelIdioma();
        nivelIdioma.setNivelIdiomaId(dto.getNivelIdiomaId());

        PerfilIdioma perfilIdioma = new PerfilIdioma();
        perfilIdioma.setPerfil(perfil);
        perfilIdioma.setIdioma(idioma);
        perfilIdioma.setNivelIdioma(nivelIdioma);

        return perfilIdioma;
    }

    public static PerfilIdiomaDto toDto(PerfilIdioma perfilIdioma){
        PerfilIdiomaDto dto = new PerfilIdiomaDto();
        dto.setPerfilIdiomaId(perfilIdioma.getPerfilIdiomaId());
        dto.setPerfilId(perfilIdioma.getPerfil().getPerfilId());
        dto.setIdiomaId(perfilIdioma.getIdioma().getIdiomaId());
        dto.setNivelIdiomaId(perfilIdioma.getNivelIdioma().getNivelIdiomaId());
        return dto;
    }

    public static List<PerfilIdiomaDto> toListDto(List<PerfilIdioma> perfilIdiomaList) {
        return perfilIdiomaList.stream().map(perfilIdioma -> toDto(perfilIdioma))
                .collect(Collectors.toList());
    }
}
