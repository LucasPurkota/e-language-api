package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, UUID> {
    List<Perfil> findByUsuario_UsuarioId(UUID usuarioId);

    @Query(value = "SELECT perfil.* FROM perfil " +
            "WHERE perfil.tipo_perfil_id = :tipoPerfilId",
            nativeQuery = true)
    List<Perfil> findByTipoPerfil_PerfilId(@Param("tipoPerfilId") int tipoPerfilId);
}
