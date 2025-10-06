package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.QuestaoAula;
import com.tcc.e_language_api.repository.QuestaoAulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class QuestaoAulaService {
    private final QuestaoAulaRepository questaoAulaRepository;

    @Transactional
    public void create(QuestaoAula questaoAula, List<String> tipoPerfil){
        if (!tipoPerfil.contains("Admin")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        questaoAulaRepository.save(questaoAula);
    }

    @Transactional
    public QuestaoAula getById(UUID id) {
        return questaoAulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Questão Aula não encontrada"));
    }

    @Transactional
    public List<QuestaoAula> getAll() {
        return questaoAulaRepository.findAll();
    }
}
