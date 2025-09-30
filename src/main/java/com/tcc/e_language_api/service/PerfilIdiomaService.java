package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.PerfilIdioma;
import com.tcc.e_language_api.repository.PerfilIdiomaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PerfilIdiomaService {
    private final PerfilIdiomaRepository perfilIdiomaRepository;

    @Transactional
    public void create(PerfilIdioma perfilIdioma){
        perfilIdiomaRepository.save(perfilIdioma);
    }
}
