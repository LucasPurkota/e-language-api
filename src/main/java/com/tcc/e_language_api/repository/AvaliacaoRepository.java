package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.Avaliacao;
import com.tcc.e_language_api.entity.PerfilIdioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {
    List<Avaliacao> findByAlunoUnidadeAlunoUnidadeId(@Param("aluno_unidade_id") UUID alunoUnidadeId);

    @Query(value = "SELECT a.* FROM avaliacao a " +
            "WHERE a.status_id = 1 AND a.aluno_unidade_id = :aluno_unidade_id ",
            nativeQuery = true)
    Optional<Avaliacao> findPendente(@Param("aluno_unidade_id") UUID alunoUnidadeId);

    // Query nativa para testar
    @Query(value = "SELECT avaliacao.* FROM avaliacao WHERE avaliacao_id = :id", nativeQuery = true)
    Optional<Avaliacao> findByIdNative(@Param("id") UUID id);
}
