package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.NivelIdioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelIdiomaRepository extends JpaRepository<NivelIdioma, Integer> {
}