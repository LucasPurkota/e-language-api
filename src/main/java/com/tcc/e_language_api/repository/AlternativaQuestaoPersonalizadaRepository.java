package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.AlternativaQuestaoPersonalizada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlternativaQuestaoPersonalizadaRepository extends JpaRepository<AlternativaQuestaoPersonalizada, UUID> {
}
