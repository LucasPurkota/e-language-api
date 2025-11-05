package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.AvaliacaoSatisfacaoProfessor;
import com.tcc.e_language_api.repository.AvaliacaoSatisfacaoProfessorRepository;
import com.tcc.e_language_api.web.dto.AvaliacaoSatisfacaoProfessorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvaliacaoSatisfacaoProfessorService {
    private final AvaliacaoSatisfacaoProfessorRepository avaliacaoSatisfacaoProfessorRepository;

    @Transactional
    public void create(AvaliacaoSatisfacaoProfessor avaliacaoSatisfacaoProfessor) {
        avaliacaoSatisfacaoProfessorRepository.save(avaliacaoSatisfacaoProfessor);
    }

    @Transactional
    public void update(UUID id, AvaliacaoSatisfacaoProfessorDto dto) {
        AvaliacaoSatisfacaoProfessor avaliacaoSatisfacaoProfessor = getById(id);

        avaliacaoSatisfacaoProfessor.setAvaliacao(dto.getAvaliacao());
        avaliacaoSatisfacaoProfessor.setPontos(dto.getPontos());
    }

    @Transactional
    public void delete(UUID id) {
        getById(id);

        avaliacaoSatisfacaoProfessorRepository.deleteById(id);
    }

    @Transactional
    public AvaliacaoSatisfacaoProfessor getById(UUID id) {
        return avaliacaoSatisfacaoProfessorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação Satisfação não encontrada"));
    }

    @Transactional
    public List<AvaliacaoSatisfacaoProfessor> getByProfessor(UUID professorId) {
        return avaliacaoSatisfacaoProfessorRepository.findByProfessor(professorId);
    }
}
