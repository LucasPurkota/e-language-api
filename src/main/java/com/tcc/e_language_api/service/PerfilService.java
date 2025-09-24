package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.entity.Usuario;
import com.tcc.e_language_api.repository.PerfilRepository;
import com.tcc.e_language_api.repository.UsuarioRepository;
import com.tcc.e_language_api.web.dto.PerfilDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PerfilService {
    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;

//    @Transactional
//    public void create(PerfilDto perfilDto) {
//        try {
//            perfilRepository.save(UsuarioMapper.toEntity(perfilDto, new Perfil()));
//        } catch (Exception e) {
//            throw new RuntimeException("Error creating Usuario: " + e.getMessage());
//        }
//    }
    @Transactional
    public void create(PerfilDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Perfil.Tipo tipo = Perfil.Tipo.valueOf(dto.getTipoPerfil());
        List<Perfil> perfis = perfilRepository.findByUsuario_UsuarioId(dto.getUsuarioId());

        boolean jaTemPerfil = perfis.stream().anyMatch(p -> p.getTipoPerfil() == tipo);
        if (jaTemPerfil) {
            throw new RuntimeException("Usuário já possui perfil " + tipo);
        }

        long count = perfis.stream().filter(p -> p.getTipoPerfil() == Perfil.Tipo.ALUNO || p.getTipoPerfil() == Perfil.Tipo.PROFESSOR).count();
        if (count >= 2) {
            throw new RuntimeException("Usuário não pode ter mais de dois perfis (ALUNO e PROFESSOR)");
        }

        Perfil perfil = new Perfil();
        perfil.setUsuario(usuario);
        perfil.setTipoPerfil(tipo);
        perfilRepository.save(perfil);
    }

    public List<Perfil> getByUserId(UUID userId) {
        try {
            List<Perfil> perfil = perfilRepository.findByUsuario_UsuarioId(userId);
            return perfil;
        } catch (Exception e) {
            throw new RuntimeException("Error creating Usuario: " + e.getMessage());
        }
    }
}
