package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PerfilService {
    private final PerfilRepository perfilRepository;

//    @Transactional
//    public void create(PerfilDto perfilDto) {
//        try {
//            perfilRepository.save(UsuarioMapper.toEntity(perfilDto, new Perfil()));
//        } catch (Exception e) {
//            throw new RuntimeException("Error creating Usuario: " + e.getMessage());
//        }
//    }

    public List<Perfil> getByUserId(UUID userId) {
        try {
            List<Perfil> perfil = perfilRepository.findByUsuario_UsuarioId(userId);
            return perfil;
        } catch (Exception e) {
            throw new RuntimeException("Error creating Usuario: " + e.getMessage());
        }
    }
}
