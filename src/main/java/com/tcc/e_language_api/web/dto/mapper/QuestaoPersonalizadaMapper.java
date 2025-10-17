package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.*;
import com.tcc.e_language_api.web.dto.QuestaoPersonalizadaDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class QuestaoPersonalizadaMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static QuestaoPersonalizada toEntity(QuestaoPersonalizadaDto dto){
        QuestaoPersonalizada questaoPersonalizada = modelMapper.map(dto, QuestaoPersonalizada.class);

        Idioma idioma = new Idioma();
        idioma.setIdiomaId(dto.getIdiomaId());

        AlunoProfessor alunoProfessor = new AlunoProfessor();
        alunoProfessor.setAlunoProfessorId(dto.getAlunoProfessorId());

        NivelDificuldade nivelDificuldade = new NivelDificuldade();
        nivelDificuldade.setNivelDificuldadeId(dto.getNivelDificuldadeId());

        TipoQuestao tipoQuestao = new TipoQuestao();
        tipoQuestao.setTipoQuestaoId(dto.getTipoQuestaoId());

        questaoPersonalizada.setIdioma(idioma);
        questaoPersonalizada.setAlunoProfessor(alunoProfessor);
        questaoPersonalizada.setNivelDificuldade(nivelDificuldade);
        questaoPersonalizada.setTipoQuestao(tipoQuestao);

        return questaoPersonalizada;
    }
}
