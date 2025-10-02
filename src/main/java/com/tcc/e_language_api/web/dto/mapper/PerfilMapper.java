package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.web.dto.perfil.response.PerfilResponse;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PerfilMapper {
    
    private static final ModelMapper mapper = new ModelMapper();
    
    public static PerfilResponse toResponse(Perfil entity) {
        PerfilResponse response = mapper.map(entity, PerfilResponse.class);
        
        // Mapear dados do TipoPerfil
        if (entity.getTipoPerfil() != null) {
            response.setTipoPerfilId(entity.getTipoPerfil().getTipoPerfilId());
            response.setTipoPerfilDescricao(entity.getTipoPerfil().getDescricao());
        }
        
        return response;
    }
    
    public static List<PerfilResponse> toResponseList(List<Perfil> entities) {
        if (entities == null) return null;
        return entities.stream()
            .map(PerfilMapper::toResponse)
            .collect(Collectors.toList());
    }
}