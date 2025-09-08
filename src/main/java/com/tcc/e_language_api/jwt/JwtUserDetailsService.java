package com.tcc.e_language_api.jwt;

import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.entity.Usuario;
import com.tcc.e_language_api.service.PerfilService;
import com.tcc.e_language_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;
    private final PerfilService perfilService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByEmail(email);
        List<Perfil> perfil = perfilService.getByUserId(usuario.getUsuarioId());
        usuario.setPerfil(perfil);
        return new JwtUserDetails(usuario);
    }



    public JwtToken getTokenAuthenticated(String email) {
        Usuario usuario = usuarioService.getByEmail(email);
        List<String> perfis = new ArrayList<>();
        for (int i = 0; i < usuario.getPerfil().toArray().length; i++){
            perfis.add(usuario.getPerfil().get(i).getTipoPerfil().name());
        }

        return JwtUtils.createToken(usuario.getEmail(), perfis);
    }
}
