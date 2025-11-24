package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.AlunoUnidade;
import com.tcc.e_language_api.entity.AlunoUnidadeAula;
import com.tcc.e_language_api.repository.AlunoUnidadeAulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlunoUnidadeAulaService {
    private final AlunoUnidadeAulaRepository alunoUnidadeAulaRepository;

    @Transactional
    public void create(AlunoUnidadeAula alunoUnidadeAula) {
        alunoUnidadeAulaRepository.save(alunoUnidadeAula);
    }

    @Transactional
    public List<AlunoUnidadeAula> getByUnidade(UUID alunoUnidadeId) {
        return alunoUnidadeAulaRepository.findByAlunoUnidade(alunoUnidadeId);
    }

    @Transactional
    public AlunoUnidadeAula getNext(UUID idiomaId, int numero) {
        return alunoUnidadeAulaRepository.findNext(idiomaId, numero)
                .orElseThrow(() -> new RuntimeException("não foi encontrado uma proxima aula"));
    }
}
