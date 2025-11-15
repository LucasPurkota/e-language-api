package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.web.dto.RankingDto;
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

        if (entity.getUsuario() != null) {
            response.setNome(entity.getUsuario().getNome());
        }
        
        return response;
    }
    
    public static List<PerfilResponse> toResponseList(List<Perfil> entities) {
        if (entities == null) return null;
        return entities.stream()
            .map(PerfilMapper::toResponse)
            .collect(Collectors.toList());
    }

    public static RankingDto toRankingDto(Perfil perfil) {
        RankingDto dto = new RankingDto();
        dto.setNomeAluno(perfil.getUsuario().getNome());
        dto.setPontosRanking(perfil.getPontosRanking());
        dto.setPosicaoRanking(perfil.getPosicaoRanking());
        return dto;
    }

    public static List<RankingDto> toListRanking(List<Perfil> entities) {
        return entities.stream()
                .map(entity -> toRankingDto(entity))
                .collect(Collectors.toList());
    }
}