package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.Chat;
import com.tcc.e_language_api.entity.DesafioDiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DesafioDiarioRepository extends JpaRepository<DesafioDiario, UUID> {
    @Query(value = "SELECT desafio_diario.* FROM desafio_diario " +
            "WHERE perfil_idioma_id = :perfilIdiomaId AND data_desafio = :dataDesafio",
            nativeQuery = true)
    List<DesafioDiario> findDesafios(@Param("perfilIdiomaId") UUID perfilIdiomaId, @Param("dataDesafio") LocalDate dataDesafio);
}
