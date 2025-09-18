package com.tcc.e_language_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.e_language_api.entity.Nivelamento;

@Repository
public interface NivelamentoRepository extends JpaRepository<Nivelamento, UUID>{
}
