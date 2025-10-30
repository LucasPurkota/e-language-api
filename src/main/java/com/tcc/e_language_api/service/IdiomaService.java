package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.Idioma;
import com.tcc.e_language_api.entity.PerfilIdioma;
import com.tcc.e_language_api.exception.EntityNotFoundException;
import com.tcc.e_language_api.repository.IdiomaRepository;
import com.tcc.e_language_api.web.dto.IdiomaDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class IdiomaService {
    private final IdiomaRepository idiomaRepository;

    @Transactional
    public void create(Idioma idioma, List<String> tipoPerfil) {
        try {
            if (!tipoPerfil.contains("Admin")) {
                throw new RuntimeException("Usario não contem permisão para essa tarefa");
            }
            idiomaRepository.save(idioma);
        } catch (Exception e) {
            throw new RuntimeException("Error creating Idioma: " + e.getMessage());
        }
    }

    @Transactional
    public void update(UUID idiomaId, IdiomaDto dto, List<String> tipoPerfil) {
        if (!tipoPerfil.contains("Admin")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }
        Idioma idioma = getById(idiomaId);
        idioma.setNome(dto.getNome());
        idioma.setSigla(dto.getSigla());
    }

    @Transactional
    public void delete(UUID idiomaId, List<String> tipoPerfil){
        if (!tipoPerfil.contains("Admin")) {
            throw new RuntimeException("Usario não contem permisão para essa tarefa");
        }

        getById(idiomaId);
        idiomaRepository.deleteById(idiomaId);
    }

    @Transactional
    public Idioma getById(UUID idiomaId){
        return idiomaRepository.findById(idiomaId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Idioma com id '%s' não encontrado", idiomaId)));
    }

    @Transactional
    public List<Idioma> getAll() {
        try {
            return idiomaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching idiomas: " + e.getMessage());
        }
    }

    @org.springframework.transaction.annotation.Transactional
    public List<Idioma> getByPerfil(UUID perfilId){
        return idiomaRepository.findByPerfilId(perfilId);
    }
}
