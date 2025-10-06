package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.AlternativaQuestaoAula;
import com.tcc.e_language_api.repository.AlternativaQuestaoAulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AlternativaQuestaoAulaService {
    private final AlternativaQuestaoAulaRepository alternativaQuestaoAulaRepository;

    @Transactional
    public void create(AlternativaQuestaoAula alternativaQuestaoAula, List<String> tipoPerfil) {
        if (!tipoPerfil.contains("Admin")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }
        alternativaQuestaoAulaRepository.save(alternativaQuestaoAula);
    }

    @Transactional
    public List<AlternativaQuestaoAula> getAll() {
        return alternativaQuestaoAulaRepository.findAll();
    }

}
