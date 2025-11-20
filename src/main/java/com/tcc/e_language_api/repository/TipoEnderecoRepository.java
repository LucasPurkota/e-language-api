package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.TipoEndereco;
import com.tcc.e_language_api.entity.TipoQuestao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoEnderecoRepository extends JpaRepository<TipoEndereco, Integer> {
}
