package com.tcc.e_language_api.repository;

import java.util.List;
import java.util.UUID;

import com.tcc.e_language_api.entity.QuestaoAula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tcc.e_language_api.entity.Nivelamento;

@Repository
public interface NivelamentoRepository extends JpaRepository<Nivelamento, UUID>{
    @Query(value = "SELECT n.* FROM nivelamento n " +
            "LEFT JOIN perfil_idioma pi ON pi.perfil_idioma_id = n.perfil_idioma_id " +
            "LEFT JOIN idioma i ON i.idioma_id = pi.idioma_id " +
            "WHERE i.nome = :idioma AND n.status_id = 1 AND pi.perfil_id = :perfil_id",
            nativeQuery = true)
    Nivelamento findNivelamentoPendente(@Param("idioma") String idioma, @Param("perfil_id") UUID perfilId);
}
