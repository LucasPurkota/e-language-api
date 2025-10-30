package com.tcc.e_language_api.repository;

import java.util.List;
import java.util.UUID;

import com.tcc.e_language_api.entity.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tcc.e_language_api.entity.QuestaoAula;

@Repository
public interface QuestaoAulaRepository extends JpaRepository<QuestaoAula, UUID>{
    @Query(value = "SELECT q.* FROM questao_aula q " +
            "LEFT JOIN aula a ON q.aula_id = a.aula_id " +
            "LEFT JOIN unidade u ON a.unidade_id = u.unidade_id " +
            "LEFT JOIN idioma i ON u.idioma_id = i.idioma_id " +
            "WHERE i.nome = :idioma AND q.nivel_dificuldade_id = :nivel_dificuldade " +
            "AND u.nivel_idioma_id = :nivel_idioma " +
            "ORDER BY RANDOM() LIMIT 3",
            nativeQuery = true)
    List<QuestaoAula> findThreeQuestionByNivel(@Param("idioma") String idioma, @Param("nivel_dificuldade") int nivelDificuldade,
                                              @Param("nivel_idioma") int nivelIdioma);

    @Query(value = "SELECT q.* FROM questao_aula q " +
            "LEFT JOIN aula a ON q.aula_id = a.aula_id " +
            "LEFT JOIN unidade u ON a.unidade_id = u.unidade_id " +
            "WHERE u.unidade_id = :unidade_id " +
            "ORDER BY RANDOM() LIMIT 10",
            nativeQuery = true)
    List<QuestaoAula> findTenQuestionsByUnidade(@Param("unidade_id") UUID unidadeId);

    List<QuestaoAula> findByAulaAulaId(UUID aulaId);
}
