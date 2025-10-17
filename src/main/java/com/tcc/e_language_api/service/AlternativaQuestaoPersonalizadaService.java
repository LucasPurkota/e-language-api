package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.AlternativaQuestaoPersonalizada;
import com.tcc.e_language_api.repository.AlternativaQuestaoPersonalizadaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlternativaQuestaoPersonalizadaService {
    private final AlternativaQuestaoPersonalizadaRepository alternativaQuestaoPersonalizadaRepository;

    @Transactional
    public void create(AlternativaQuestaoPersonalizada alternativaQuestaoPersonalizada, List<String> perfil) {
        if (!perfil.contains("Professor")) {
            throw new RuntimeException("Você não tem acesso para esta tarefa!");
        }

        alternativaQuestaoPersonalizadaRepository.save(alternativaQuestaoPersonalizada);
    }
}
