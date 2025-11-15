package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.AlunoUnidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlunoUnidadeRepository extends JpaRepository<AlunoUnidade, UUID> {
    @Query(value = "SELECT au.* FROM aluno_unidade au " +
            "WHERE au.aluno_id = :alunoId AND au.unidade_id = :unidadeId;",
            nativeQuery = true)
    Optional<AlunoUnidade> findByAlunoAndUnidade(@Param("alunoId") UUID alunoId, @Param("unidadeId") UUID unidadeId);
}
