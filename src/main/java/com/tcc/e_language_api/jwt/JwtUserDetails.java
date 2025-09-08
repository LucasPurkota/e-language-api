package com.tcc.e_language_api.jwt;

import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

public class JwtUserDetails extends User {

    private Usuario usuario;

    public JwtUserDetails(Usuario usuario) {

        super(usuario.getEmail(), usuario.getSenha(), mapPerfisParaAuthorities(usuario));
        this.usuario = usuario;
    }
    private static List<GrantedAuthority> mapPerfisParaAuthorities(Usuario usuario) {
        if (usuario.getPerfil() == null) {
            return Collections.emptyList();       // evita null
        }

        return usuario.getPerfil().stream()
                .map(Perfil::getTipoPerfil)
                .filter(Objects::nonNull)
                .map(tipo -> tipo.name())                // converte enum para String
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }



    public UUID getId() {
        return this.usuario.getUsuarioId();
    }

    public List<String> getRole() {
        List<String> perfis = new ArrayList<>();
        for (int i = 0; i < this.usuario.getPerfil().toArray().length; i++){
            perfis.add(this.usuario.getPerfil().get(i).getTipoPerfil().name());
        }
        return perfis;
    }
}

