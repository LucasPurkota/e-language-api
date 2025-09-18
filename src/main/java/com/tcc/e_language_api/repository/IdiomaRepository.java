package com.tcc.e_language_api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.e_language_api.entity.Idioma;

public interface IdiomaRepository extends JpaRepository<Idioma, UUID> {
    Optional<Idioma> findByNome(String nome);
}
