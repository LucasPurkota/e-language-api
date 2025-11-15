package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.AlunoProfessor;
import com.tcc.e_language_api.entity.AlunoUnidade;
import com.tcc.e_language_api.repository.AlunoUnidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlunoUnidadeService {
    private final AlunoUnidadeRepository alunoUnidadeRepository;

    @Transactional
    public void create(AlunoUnidade alunoUnidade) {
        alunoUnidadeRepository.save(alunoUnidade);
    }

    @Transactional
    public void delete(UUID id) {
        getById(id);
        alunoUnidadeRepository.deleteById(id);
    }

    @Transactional
    public AlunoUnidade getById(UUID id) {
        return alunoUnidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno Unidade not found"));
    }

    @Transactional
    public List<AlunoUnidade> getAll() {
        return alunoUnidadeRepository.findAll();
    }

    @Transactional
    public AlunoUnidade getByAlunoAndUnidade(UUID alunoId, UUID unidadeId) {
        return alunoUnidadeRepository.findByAlunoAndUnidade(alunoId, unidadeId)
                .orElseThrow(() -> new RuntimeException("Aluno Unidade not found"));
    }
}
