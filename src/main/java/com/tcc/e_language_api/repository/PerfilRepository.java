package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, UUID> {
    List<Perfil> findByUsuario_UsuarioId(UUID usuarioId);

    @Query(value = "SELECT perfil.* FROM perfil " +
            "WHERE perfil.tipo_perfil_id = :tipoPerfilId",
            nativeQuery = true)
    List<Perfil> findByTipoPerfil_PerfilId(@Param("tipoPerfilId") int tipoPerfilId);

    @Query(value = "SELECT p.* FROM perfil p " +
            "LEFT JOIN public.usuario u ON u.usuario_id = p.usuario_id " +
            "LEFT JOIN public.tipo_perfil tp ON tp.tipo_perfil_id = p.tipo_perfil_id " +
            "WHERE tp.descricao = :tipo_perfil AND u.email = :usuario",
            nativeQuery = true)
    Optional<Perfil> findByTipoPerfilAndUsuario(@Param("tipo_perfil") String tipoPerfil, @Param("usuario") String usuario);

    @Query(value = "SELECT u.nome, p.pontos_ranking, ROW_NUMBER() OVER (ORDER BY p.pontos_ranking DESC) as posicao_ranking FROM perfil p " +
            "LEFT JOIN public.usuario u ON u.usuario_id = p.usuario_id " +
            "WHERE p.tipo_perfil_id = 1 " +
            "ORDER BY p.pontos_ranking DESC;",
            nativeQuery = true)
    List<Object[]> findRanking();

    @Query(value = "SELECT u.nome, pi.pontos_ranking, ROW_NUMBER() OVER (ORDER BY pi.pontos_ranking DESC) as posicao_ranking FROM perfil p " +
            "LEFT JOIN public.usuario u ON u.usuario_id = p.usuario_id " +
            "LEFT JOIN public.perfil_idioma pi ON pi.perfil_id = p.perfil_id " +
            "LEFT JOIN public.idioma i ON i.idioma_id = pi.idioma_id " +
            "WHERE p.tipo_perfil_id = 1 AND i.nome = :idioma " +
            "ORDER BY p.pontos_ranking DESC;",
            nativeQuery = true)
    List<Object[]> findRankingByIdioma(@Param("idioma") String idioma);
}
