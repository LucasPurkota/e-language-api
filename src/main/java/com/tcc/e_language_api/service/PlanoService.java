package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.Plano;
import com.tcc.e_language_api.repository.PlanoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanoService {
    private final PlanoRepository planoRepository;

    @Transactional
    public void create(Plano plano, List<String> perfil) {
        if (!perfil.contains("Admin")){
            throw new RuntimeException("Você não tem permissão para realizar esta tarefa");
        }

        planoRepository.save(plano);
    }
}
