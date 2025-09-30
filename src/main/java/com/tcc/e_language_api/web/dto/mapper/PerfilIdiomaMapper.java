package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.*;
import com.tcc.e_language_api.web.dto.PerfilIdiomaDto;
import lombok.RequiredArgsConstructor;

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
}
