package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.AlunoPlano;
import com.tcc.e_language_api.entity.AlunoUnidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AlunoPlanoRepository extends JpaRepository<AlunoPlano, UUID> {
    @Query(value = "SELECT ap.* FROM aluno_plano ap " +
            "WHERE ap.aluno_id = :alunoId;",
            nativeQuery = true)
    Optional<AlunoPlano> findByAluno(@Param("alunoId") UUID alunoId);
}
