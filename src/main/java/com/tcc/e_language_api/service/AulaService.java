package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.Aula;
import com.tcc.e_language_api.entity.Unidade;
import com.tcc.e_language_api.repository.AulaRepository;
import com.tcc.e_language_api.web.dto.AulaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulaRepository aulaRepository;

    @Transactional
    public void create(Aula aula, List<String> tipoPerfil) {
        if (!tipoPerfil.contains("Admin")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        if (aulaRepository.existsByTitulo(aula.getTitulo())) {
            throw new RuntimeException("Já existe uma unidade com este título");
        }

        if (aulaRepository.existsByConteudo(aula.getConteudo())) {
            throw new RuntimeException("Já existe uma unidade com este conteúdo");
        }

        aulaRepository.save(aula);
    }

    @Transactional
    public void update(UUID aulaId, AulaDto dto, List<String> tipoPerfil) {
        if (!tipoPerfil.contains("Admin")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        Aula aula = getById(aulaId);

        Unidade unidade = new Unidade();
        unidade.setUnidadeId(dto.getUnidadeId());

        aula.setConteudo(dto.getConteudo());
        aula.setTitulo(dto.getTitulo());
        aula.setNumero(dto.getNumero());
        aula.setLinkVideo(dto.getLinkVideo());
        aula.setUnidade(unidade);
    }

    @Transactional
    public void delete(UUID aulaId, List<String> tipoPerfil) {
        if (!tipoPerfil.contains("Admin")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        getById(aulaId);

        aulaRepository.deleteById(aulaId);
    }

    @Transactional
    public Aula getById(UUID id){
        return aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula not found"));
    }

    public List<Aula> getByUnidade(UUID unidadeId) {
        return aulaRepository.findByUnidadeUnidadeId(unidadeId);
    }
}
