package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.entity.Usuario;
import com.tcc.e_language_api.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RankingService {
    private final PerfilRepository perfilRepository;

    @Transactional
    public List<Perfil> getRankigTotal(){
        List<Object[]> objects = perfilRepository.findRanking();

        List<Perfil> perfil = new ArrayList<>();

        for(Object[] object : objects){
            Usuario usuario = new Usuario();
            usuario.setNome(object[0].toString());
            Perfil p = new Perfil();
            p.setUsuario(usuario);
            p.setPontosRanking(Double.parseDouble(object[1].toString()));
            p.setPosicaoRanking(Integer.parseInt(object[2].toString()));
            perfil.add(p);
        }
        return perfil;
    }

    @Transactional
    public List<Perfil> getRankigByIdioma(String idioma){
        List<Object[]> objects = perfilRepository.findRankingByIdioma(idioma);

        List<Perfil> perfil = new ArrayList<>();

        for(Object[] object : objects){
            Usuario usuario = new Usuario();
            usuario.setNome(object[0].toString());
            Perfil p = new Perfil();
            p.setUsuario(usuario);
            p.setPontosRanking(Double.parseDouble(object[1].toString()));
            p.setPosicaoRanking(Integer.parseInt(object[2].toString()));
            perfil.add(p);
        }
        return perfil;
    }
}
