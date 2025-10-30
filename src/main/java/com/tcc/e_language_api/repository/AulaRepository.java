package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.UUID;

@Repository
public interface AulaRepository extends JpaRepository<Aula, UUID> {
    boolean existsByTitulo(String titulo);
    boolean existsByConteudo(String conteudo);
    List<Aula> findByUnidadeUnidadeId(UUID unidadeId);
}
