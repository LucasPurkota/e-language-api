package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.AlternativaQuestaoAula;
import com.tcc.e_language_api.repository.AlternativaQuestaoAulaRepository;
import com.tcc.e_language_api.web.dto.AlternativaQuestaoAulaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
    public void update(UUID id, AlternativaQuestaoAulaDto dto, List<String> tipoPerfil){
        if (!tipoPerfil.contains("Admin")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        AlternativaQuestaoAula alternativa = getById(id);

        alternativa.setAlternativa(dto.getAlternativa());
        alternativa.setDescricao(dto.getDescricao());
    }

    @Transactional
    public void delete(UUID id, List<String> tipoPerfil) {
        if (!tipoPerfil.contains("Admin")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        getById(id);

        alternativaQuestaoAulaRepository.deleteById(id);
    }

    @Transactional
    public AlternativaQuestaoAula getById(UUID id) {
        return alternativaQuestaoAulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alternativa Questao Aula not found"));
    }

    @Transactional
    public List<AlternativaQuestaoAula> getByQuestao(UUID questaoId){
        return alternativaQuestaoAulaRepository.findByQuestaoAulaQuestaoAulaId(questaoId);
    }

    @Transactional
    public List<AlternativaQuestaoAula> getAll() {
        return alternativaQuestaoAulaRepository.findAll();
    }

}
