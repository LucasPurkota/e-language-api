package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.AvaliacaoSatisfacaoProfessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AvaliacaoSatisfacaoProfessorRepository extends JpaRepository<AvaliacaoSatisfacaoProfessor, UUID> {
    @Query(value = "SELECT asp.* FROM avaliacao_satisfacao_professor asp " +
            "LEFT JOIN aluno_professor ap ON ap.aluno_professor_id = asp.aluno_professor_id " +
            "WHERE ap.professor_id = :professorId",
            nativeQuery = true)
    List<AvaliacaoSatisfacaoProfessor> findByProfessor(@Param("professorId") UUID professorId);
}
