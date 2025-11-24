package com.tcc.e_language_api.service;

import com.tcc.e_language_api.entity.*;
import com.tcc.e_language_api.entity.AlunoUnidadeAula;
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
    private final AlunoUnidadeAulaService alunoUnidadeAulaService;
    private final AulaService aulaService;

    @Transactional
    public void create(PerfilIdioma perfilIdioma){
        Perfil perfil = perfilService.getById(perfilIdioma.getPerfil().getPerfilId());

        if (perfil.getTipoPerfil().getDescricao().equals("Aluno")) {
            List<Unidade> unidades = unidadeService.getByIdiomaId(perfilIdioma.getIdioma().getIdiomaId());
            for (Unidade unidade : unidades) {
                AlunoUnidade alunoUnidade = new AlunoUnidade();
                alunoUnidade.setUnidade(unidade);
                alunoUnidade.setAluno(perfil);

                Status statusUnidade = new Status();
                if (unidade.getNumero() == 1) {
                    statusUnidade.setStatusId(2);
                }else {
                    statusUnidade.setStatusId(1);
                }

                alunoUnidade.setStatus(statusUnidade);
                alunoUnidadeService.create(alunoUnidade);

                List<Aula> aulas = aulaService.getByUnidade(unidade.getUnidadeId());
                for (Aula aula : aulas) {
                    AlunoUnidadeAula alunoUnidadeAula = new AlunoUnidadeAula();

                    alunoUnidadeAula.setAula(aula);
                    alunoUnidadeAula.setAlunoUnidade(alunoUnidade);
                    Status statusAula = new Status();
                    if ((unidade.getNumero() == 1) && (aula.getNumero() == 1)) {
                        statusAula.setStatusId(2);
                    }else {
                        statusAula.setStatusId(1);
                    }
                    alunoUnidadeAula.setStatus(statusAula);

                    alunoUnidadeAulaService.create(alunoUnidadeAula);
                }
            }
        }
        perfilIdioma.setPontosRanking(0.0);
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
        List<PerfilIdioma> perfilIdiomaList =  perfilIdiomaRepository.findByPerfilPerfilId(perfilId);

        Perfil perfil = perfilService.getById(perfilId);

        if (perfil.getTipoPerfil().getDescricao().equals("Aluno")) {
            for (PerfilIdioma perfilIdioma : perfilIdiomaList) {
                int posicao  = perfilIdiomaRepository.findPosicaoRanking(perfilIdioma.getPerfilIdiomaId());
                perfilIdioma.setPosicaoRanking(posicao);
            }
        }

        return perfilIdiomaList;
    }

    @Transactional
    public PerfilIdioma getByPerfilAndIdioma(UUID perfilId, UUID idiomaId) {
        return perfilIdiomaRepository.findByPerfilAndIdioma(idiomaId, perfilId)
                .orElseThrow(() -> new RuntimeException("Aluno Idioma not found"));
    }

}