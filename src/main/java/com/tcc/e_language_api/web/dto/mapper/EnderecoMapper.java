package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.Endereco;
import com.tcc.e_language_api.web.dto.endereco.request.EnderecoCreateRequest;
import com.tcc.e_language_api.web.dto.endereco.response.EnderecoResponse;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class EnderecoMapper {
    
    private static final ModelMapper mapper = new ModelMapper();
    
    public static Endereco toEntity(EnderecoCreateRequest dto) {
        return mapper.map(dto, Endereco.class);
    }
    
    public static EnderecoResponse toResponse(Endereco entity) {
        return mapper.map(entity, EnderecoResponse.class);
    }
    
    public static List<Endereco> toEntityList(List<EnderecoCreateRequest> dtos) {
        if (dtos == null) return null;
        return dtos.stream()
            .map(EnderecoMapper::toEntity)
            .collect(Collectors.toList());
    }
    
    public static List<EnderecoResponse> toResponseList(List<Endereco> entities) {
        if (entities == null) return null;
        return entities.stream()
            .map(EnderecoMapper::toResponse)
            .collect(Collectors.toList());
    }
}