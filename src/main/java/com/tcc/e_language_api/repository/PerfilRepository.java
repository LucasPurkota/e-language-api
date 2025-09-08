package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, UUID> {
    List<Perfil> findByUsuario_UsuarioId(UUID usuarioId);
}
