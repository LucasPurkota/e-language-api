package com.tcc.e_language_api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcc.e_language_api.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    
    Optional<Usuario> findByEmail(String email);
    
    Optional<Usuario> findByCpf(String cpf);
    
    boolean existsByEmail(String email);
    
    boolean existsByCpf(String cpf);
}
