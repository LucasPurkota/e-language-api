package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.Idioma;
import com.tcc.e_language_api.repository.IdiomaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
