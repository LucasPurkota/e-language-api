package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.Plano;
import com.tcc.e_language_api.repository.PlanoRepository;
import com.tcc.e_language_api.web.dto.PlanoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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

    @Transactional
    public void update(UUID planoId, PlanoDto dto, List<String> perfil) {
        if (!perfil.contains("Admin")){
            throw new RuntimeException("Você não tem permissão para realizar esta tarefa");
        }

        Plano plano = getById(planoId);

        plano.setDescricao(dto.getDescricao());
        plano.setTitulo(dto.getTitulo());
    }

    @Transactional
    public void delete(UUID id, List<String> perfil) {
        if (!perfil.contains("Admin")){
            throw new RuntimeException("Você não tem permissão para realizar esta tarefa");
        }

        getById(id);

        planoRepository.deleteById(id);
    }

    @Transactional
    public Plano getById(UUID id) {
        return planoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
    }

    @Transactional
    public List<Plano> getAll() {
        return planoRepository.findAll();
    }

}
