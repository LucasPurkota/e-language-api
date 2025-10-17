package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.AlunoUnidade;
import com.tcc.e_language_api.repository.AlunoUnidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlunoUnidadeService {
    private final AlunoUnidadeRepository alunoUnidadeRepository;

    @Transactional
    public void create(AlunoUnidade alunoUnidade) {
        alunoUnidadeRepository.save(alunoUnidade);
    }
}
