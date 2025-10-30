package com.tcc.e_language_api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.tcc.e_language_api.entity.PerfilIdioma;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.e_language_api.entity.Idioma;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IdiomaRepository extends JpaRepository<Idioma, UUID> {
    Optional<Idioma> findByNome(String nome);

    @Query(value = "SELECT idioma.* FROM idioma LEFT JOIN perfil_idioma pi ON pi.idioma_id = idioma.idioma_id WHERE perfil_id = :perfil_id",
            nativeQuery = true)
    List<Idioma> findByPerfilId(@Param("perfil_id") UUID perfilId);
}
