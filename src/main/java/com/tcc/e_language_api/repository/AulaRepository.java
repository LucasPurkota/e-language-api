package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AulaRepository extends JpaRepository<Aula, UUID> {
    boolean existsByNumero(int numero);
    boolean existsByTitulo(String titulo);
    boolean existsByLinkVideo(String linkVideo);
    boolean existsByConteudo(String conteudo);
}
