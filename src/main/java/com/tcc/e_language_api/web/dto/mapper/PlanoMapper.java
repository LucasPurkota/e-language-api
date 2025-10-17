package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.FormaPagamento;
import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.entity.Plano;
import com.tcc.e_language_api.web.dto.PlanoDto;
import com.tcc.e_language_api.web.dto.perfil.response.PerfilResponse;
import org.modelmapper.ModelMapper;

public class PlanoMapper {
    private static final ModelMapper mapper = new ModelMapper();

    public static Plano toEntity(PlanoDto dto) {
        Plano response = mapper.map(dto, Plano.class);

        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setFormaPagamentoId(dto.getFormaPagamentoId());

        response.setFormaPagamento(formaPagamento);
        return response;
    }
}
