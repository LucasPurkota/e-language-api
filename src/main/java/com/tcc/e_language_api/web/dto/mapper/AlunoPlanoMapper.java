package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.*;
import com.tcc.e_language_api.web.dto.AlternativaQuestaoPersonalizadaDto;
import com.tcc.e_language_api.web.dto.AlunoPlanoDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AlunoPlanoMapper {
    public static AlunoPlano toEntity(AlunoPlanoDto dto) {

        AlunoPlano alunoPlano = new AlunoPlano();

        Perfil aluno = new Perfil();
        aluno.setPerfilId(dto.getAlunoId());

        Plano plano = new Plano();
        plano.setPlanoId(dto.getPlanoId());

        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setFormaPagamentoId(dto.getFormaPagamentoId());

        alunoPlano.setAluno(aluno);
        alunoPlano.setPlano(plano);
        alunoPlano.setFormaPagamento(formaPagamento);
        alunoPlano.setCvc(dto.getCvc());
        alunoPlano.setNomeCartao(dto.getNomeCartao());
        alunoPlano.setVencimentoCartao(dto.getVencimentoCartao());
        alunoPlano.setNumeroCartao(dto.getNumeroCartao());
        return alunoPlano;
    }

    public static AlunoPlanoDto toDto(AlunoPlano entity) {

        AlunoPlanoDto dto = new AlunoPlanoDto();

        dto.setAlunoPlanoId(entity.getAlunoPlanoId());
        dto.setAlunoId(entity.getAluno().getPerfilId());
        dto.setPlanoId(entity.getPlano().getPlanoId());
        dto.setTituloPlano(entity.getPlano().getTitulo());
        dto.setFormaPagamentoId(entity.getFormaPagamento().getFormaPagamentoId());
        dto.setFormaPagamentoDescricao(entity.getFormaPagamento().getDescricao());
        dto.setCvc(entity.getCvc());
        dto.setNomeCartao(entity.getNumeroCartao());
        dto.setNumeroCartao(entity.getNumeroCartao());
        dto.setVencimentoCartao(entity.getVencimentoCartao());

        return dto;
    }

    public static List<AlunoPlanoDto> toListDto(List<AlunoPlano> alternativaQuestaoAula) {
        return alternativaQuestaoAula.stream().map(alternativa -> toDto(alternativa)).collect(Collectors.toList());
    }
}
