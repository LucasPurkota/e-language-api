package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.Nivelamento;
import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.entity.PerfilIdioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PerfilIdiomaRepository extends JpaRepository<PerfilIdioma, UUID> {

    @Query(value = "SELECT pi.* FROM perfil_idioma pi " +
            "LEFT JOIN idioma i ON i.idioma_id = pi.idioma_id " +
            "WHERE i.nome = :idioma_nome AND pi.perfil_id = :perfil_id",
            nativeQuery = true)
    PerfilIdioma findByIdiomaAndPerfil(@Param("idioma_nome") String idioma, @Param("perfil_id") UUID perfilId);

    @Query(value = "SELECT pi.* FROM perfil_idioma pi " +
            "WHERE pi.idioma_id = :idioma_id AND pi.perfil_id = :perfil_id",
            nativeQuery = true)
    Optional<PerfilIdioma> findByPerfilAndIdioma(@Param("idioma_id") UUID idioma, @Param("perfil_id") UUID perfilId);

    List<PerfilIdioma> findByPerfilPerfilId(@Param("perfil_id") UUID perfilId);
}
