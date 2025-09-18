package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;
import java.util.List;

@Repository
public interface UnidadeRepository extends JpaRepository<com.tcc.e_language_api.entity.Unidade, UUID> {

    Optional<Unidade> findByTitulo(String titulo);

    Optional<Unidade> findByConteudo(String conteudo);

    boolean existsByTitulo(String titulo);

    boolean existsByConteudo(String conteudo);

    List<Unidade> findByIdiomaIdiomaId(UUID idiomaId);

    List<Unidade> findByNivelIdiomaNivelIdiomaId(int nivelIdiomaId);

    List<Unidade> findByIdiomaIdiomaIdAndNivelIdiomaNivelIdiomaId(UUID idiomaId, int nivelIdiomaId);
}

