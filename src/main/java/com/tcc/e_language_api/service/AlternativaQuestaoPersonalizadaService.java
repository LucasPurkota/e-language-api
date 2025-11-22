package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.AlternativaQuestaoPersonalizada;
import com.tcc.e_language_api.repository.AlternativaQuestaoPersonalizadaRepository;
import com.tcc.e_language_api.web.dto.AlternativaQuestaoPersonalizadaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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

    @Transactional
    public void update(UUID id, AlternativaQuestaoPersonalizadaDto dto, List<String> tipoPerfil){
        if (!tipoPerfil.contains("Professor")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        AlternativaQuestaoPersonalizada alternativa = getById(id);

        alternativa.setAlternativa(dto.getAlternativa());
        alternativa.setDescricao(dto.getDescricao());
    }

    @Transactional
    public void delete(UUID id, List<String> tipoPerfil) {
        if (!tipoPerfil.contains("Professor")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        getById(id);

        alternativaQuestaoPersonalizadaRepository.deleteById(id);
    }

    @Transactional
    public AlternativaQuestaoPersonalizada getById(UUID id) {
        return alternativaQuestaoPersonalizadaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alternativa Questao Aula not found"));
    }

    @Transactional
    public List<AlternativaQuestaoPersonalizada> getByQuestao(UUID questaoId){
        return alternativaQuestaoPersonalizadaRepository.findByQuestaoPersonalizadaQuestaoPersonalizadaId(questaoId);
    }

    //update
    //delete
    //getbyid
    //getbyquestao
}
