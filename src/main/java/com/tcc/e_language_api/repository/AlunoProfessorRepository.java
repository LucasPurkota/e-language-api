package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.AlunoProfessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlunoProfessorRepository extends JpaRepository<AlunoProfessor, UUID> {
}
