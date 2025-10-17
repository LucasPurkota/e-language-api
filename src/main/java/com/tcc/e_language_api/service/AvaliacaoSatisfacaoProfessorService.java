package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.AvaliacaoSatisfacaoProfessor;
import com.tcc.e_language_api.repository.AvaliacaoSatisfacaoProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AvaliacaoSatisfacaoProfessorService {
    private final AvaliacaoSatisfacaoProfessorRepository avaliacaoSatisfacaoProfessorRepository;

    @Transactional
    public void create(AvaliacaoSatisfacaoProfessor avaliacaoSatisfacaoProfessor) {
        avaliacaoSatisfacaoProfessorRepository.save(avaliacaoSatisfacaoProfessor);
    }
}
