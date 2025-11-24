package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.AlunoUnidade;
import com.tcc.e_language_api.entity.AlunoUnidadeAula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlunoUnidadeAulaRepository extends JpaRepository<AlunoUnidadeAula, UUID> {
    @Query(value = "SELECT a.* FROM aluno_unidade_aula a " +
            "WHERE a.aluno_unidade_id = :alunoUnidadeId;",
            nativeQuery = true)
    List<AlunoUnidadeAula> findByAlunoUnidade(@Param("alunoUnidadeId") UUID alunoUnidadeId);

    @Query(value = "SELECT a.* FROM aluno_unidade_aula a " +
            "LEFT JOIN aula ON aula.aula_id = a.aula_id " +
            "LEFT JOIN aluno_unidade au ON au.aluno_unidade_id = a.aluno_unidade_id " +
            "LEFT JOIN unidade u ON u.unidade_id = au.unidade_id " +
            "WHERE aula.numero = :numero AND u.idioma_id = :idiomaId;",
            nativeQuery = true)
    Optional<AlunoUnidadeAula> findNext(@Param("idiomaId") UUID idiomaId, @Param("numero") int numero);
}
