package com.tcc.e_language_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcc.e_language_api.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    @Query(value = "SELECT * FROM usuarios WHERE email = ?1", nativeQuery = true)
    static Usuario findByEmail(String email) {
        return null;
    }
}
