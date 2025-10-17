package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.QuestaoPersonalizada;
import com.tcc.e_language_api.repository.QuestaoPersonalizadaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestaoPersonalizadaService {
    private final QuestaoPersonalizadaRepository questaoPersonalizadaRepository;

    @Transactional
    public void create(QuestaoPersonalizada questaoPersonalizada, List<String> perfil) {
        if (!perfil.contains("Professor")) {
            throw new RuntimeException("Você não tem permissão para realizar esta tarefa");
        }

        questaoPersonalizadaRepository.save(questaoPersonalizada);
    }
}
