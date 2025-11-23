package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Plano;
import com.tcc.e_language_api.web.dto.PlanoDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PlanoMapper {
    private static final ModelMapper mapper = new ModelMapper();

    public static Plano toEntity(PlanoDto dto) {
        return mapper.map(dto, Plano.class);
    }

    public static PlanoDto toDto(Plano plano){
        PlanoDto dto = new PlanoDto();
        dto.setPlanoId(plano.getPlanoId());
        dto.setDescricao(plano.getDescricao());
        dto.setTitulo(plano.getTitulo());
        dto.setValor(plano.getValor());
        return dto;
    }

    public static List<PlanoDto> toListDto(List<Plano> planos) {
        return planos.stream().map(plano -> toDto(plano)).collect(Collectors.toList());
    }
}
