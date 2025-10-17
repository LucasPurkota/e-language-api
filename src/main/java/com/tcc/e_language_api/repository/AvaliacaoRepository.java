package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {

}
