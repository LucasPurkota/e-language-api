package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.AlunoPlano;
import com.tcc.e_language_api.entity.FormaPagamento;
import com.tcc.e_language_api.repository.AlunoPlanoRepository;
import com.tcc.e_language_api.web.dto.AlunoPlanoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlunoPlanoService {
    private final AlunoPlanoRepository alunoPlanoRepository;

    @Transactional
    public void criarVinculo(AlunoPlano alunoPlano) {
        alunoPlanoRepository.save(alunoPlano);
    }

    @Transactional
    public void update(UUID alunoPlanoId, AlunoPlanoDto dto) {
        AlunoPlano alunoPlano = getById(alunoPlanoId);

        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setFormaPagamentoId(dto.getFormaPagamentoId());

        alunoPlano.setFormaPagamento(formaPagamento);
        alunoPlano.setNumeroCartao(dto.getNumeroCartao());
        alunoPlano.setNomeCartao(dto.getNomeCartao());
        alunoPlano.setVencimentoCartao(dto.getVencimentoCartao());
        alunoPlano.setCvc(dto.getCvc());
    }

    @Transactional
    public void deleteVinculo(UUID alunoPlanoId) {
        getById(alunoPlanoId);
        alunoPlanoRepository.deleteById(alunoPlanoId);
    }

    @Transactional
    public AlunoPlano getById(UUID alunoPlanoId) {
        return alunoPlanoRepository.findById(alunoPlanoId)
                .orElseThrow(() -> new RuntimeException("Aluno Plano não encontrado para o id " + alunoPlanoId));
    }

    @Transactional
    public AlunoPlano getByAluno(UUID alunoId) {
        return alunoPlanoRepository.findByAluno(alunoId)
                .orElseThrow(() -> new RuntimeException("Não encontrado nenhum vinculo de plano para o alunoId " + alunoId));
    }

}
