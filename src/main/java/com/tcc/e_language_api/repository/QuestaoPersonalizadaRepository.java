package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.QuestaoAula;
import com.tcc.e_language_api.entity.QuestaoPersonalizada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestaoPersonalizadaRepository extends JpaRepository<QuestaoPersonalizada, UUID> {
    @Query(value = "SELECT q.* FROM questao_personalizada q " +
            "LEFT JOIN aluno_professor ap ON q.aluno_professor_id = ap.aluno_professor_id " +
            "WHERE ap.aluno_id = :aluno " +
            "AND (:status IS NULL OR q.status_id = :status) " +
            "AND (:idioma IS NULL OR q.idioma_id = :idioma)",
            nativeQuery = true)
    List<QuestaoPersonalizada> findByAluno(@Param("aluno") UUID aluno, @Param("status") Integer status, @Param("idioma") UUID idioma);
}
