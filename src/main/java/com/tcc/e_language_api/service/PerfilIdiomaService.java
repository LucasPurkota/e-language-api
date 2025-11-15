package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.AlunoUnidade;
import com.tcc.e_language_api.entity.Perfil;
import com.tcc.e_language_api.entity.PerfilIdioma;
import com.tcc.e_language_api.entity.Unidade;
import com.tcc.e_language_api.repository.PerfilIdiomaRepository;
import com.tcc.e_language_api.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PerfilIdiomaService {
    private final PerfilIdiomaRepository perfilIdiomaRepository;
    private final PerfilService perfilService;
    private final UnidadeService unidadeService;
    private final AlunoUnidadeService alunoUnidadeService;

    @Transactional
    public void create(PerfilIdioma perfilIdioma){
        Perfil perfil = perfilService.getById(perfilIdioma.getPerfil().getPerfilId());

        if (perfil.getTipoPerfil().getDescricao().equals("Aluno")) {
            List<Unidade> unidades = unidadeService.getByIdiomaId(perfilIdioma.getIdioma().getIdiomaId());
            for (Unidade unidade : unidades) {
                AlunoUnidade alunoUnidade = new AlunoUnidade();
                alunoUnidade.setUnidade(unidade);
                alunoUnidade.setAluno(perfil);

                alunoUnidadeService.create(alunoUnidade);
            }
        }
        perfilIdiomaRepository.save(perfilIdioma);
    }

    @Transactional
    public void delete(UUID id) {
        getById(id);
        perfilIdiomaRepository.deleteById(id);
    }

    @Transactional
    public PerfilIdioma getById(UUID id) {
        return perfilIdiomaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno Unidade not found"));
    }

    @Transactional
    public List<PerfilIdioma> getByPerfil(UUID perfilId) {
        return perfilIdiomaRepository.findByPerfilPerfilId(perfilId);
    }

    @Transactional
    public PerfilIdioma getByPerfilAndIdioma(UUID perfilId, UUID idiomaId) {
        return perfilIdiomaRepository.findByPerfilAndIdioma(idiomaId, perfilId)
                .orElseThrow(() -> new RuntimeException("Aluno Unidade not found"));
    }

}
