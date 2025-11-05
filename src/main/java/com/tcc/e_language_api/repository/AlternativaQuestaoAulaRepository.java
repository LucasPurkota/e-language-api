package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.AlternativaQuestaoAula;
import com.tcc.e_language_api.entity.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlternativaQuestaoAulaRepository extends JpaRepository<AlternativaQuestaoAula, UUID> {
    List<AlternativaQuestaoAula> findByQuestaoAulaQuestaoAulaId(UUID questaoAulaId);
}
