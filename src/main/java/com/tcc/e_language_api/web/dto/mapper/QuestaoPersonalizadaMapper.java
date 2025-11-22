package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.*;
import com.tcc.e_language_api.web.dto.AlternativaQuestaoAulaDto;
import com.tcc.e_language_api.web.dto.AlternativaQuestaoPersonalizadaDto;
import com.tcc.e_language_api.web.dto.QuestaoAulaDto;
import com.tcc.e_language_api.web.dto.QuestaoPersonalizadaDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        Status status = new Status();
        status.setStatusId(1);

        questaoPersonalizada.setIdioma(idioma);
        questaoPersonalizada.setAlunoProfessor(alunoProfessor);
        questaoPersonalizada.setNivelDificuldade(nivelDificuldade);
        questaoPersonalizada.setTipoQuestao(tipoQuestao);
        questaoPersonalizada.setStatus(status);

        return questaoPersonalizada;
    }

    public static QuestaoPersonalizadaDto toDto(QuestaoPersonalizada questaoPersonalizada) {
        QuestaoPersonalizadaDto dto = new QuestaoPersonalizadaDto();

        dto.setQuestaoPersonalizadaId(questaoPersonalizada.getQuestaoPersonalizadaId());
        dto.setIdiomaId(questaoPersonalizada.getIdioma().getIdiomaId());
        dto.setAlunoProfessorId(questaoPersonalizada.getAlunoProfessor().getAlunoProfessorId());
        dto.setNivelDificuldadeId(questaoPersonalizada.getNivelDificuldade().getNivelDificuldadeId());
        dto.setTipoQuestaoId(questaoPersonalizada.getTipoQuestao().getTipoQuestaoId());
        dto.setEnunciado(questaoPersonalizada.getEnunciado());
        dto.setGabarito(questaoPersonalizada.getGabarito());
        dto.setStatus(questaoPersonalizada.getStatus().getDescricao());
        dto.setStatusId(questaoPersonalizada.getStatus().getStatusId());

        if ((dto.getCorreto() == null) || (questaoPersonalizada.getCorreto().equals("S"))){
            dto.setCorreto("Sim");
        }else {
            dto.setCorreto("Não");
        }

        List<AlternativaQuestaoPersonalizadaDto> alternativas = new ArrayList<>();
        for (AlternativaQuestaoPersonalizada alternativa : questaoPersonalizada.getAlternativas()) {
            AlternativaQuestaoPersonalizadaDto aDto = new AlternativaQuestaoPersonalizadaDto();
            aDto.setAlternativaQuestaoPersonalizadaId(alternativa.getAlternativaQuestaoPersonalizadaId());
            aDto.setQuestaoPersonalizadaId(alternativa.getQuestaoPersonalizada().getQuestaoPersonalizadaId());
            aDto.setAlternativa(alternativa.getAlternativa());
            aDto.setDescricao(alternativa.getDescricao());

            alternativas.add(aDto);
        }

        dto.setAlternativas(alternativas);

        return dto;
    }

    public static List<QuestaoPersonalizadaDto> toListDto(List<QuestaoPersonalizada> questaoPersonalizada) {
        return questaoPersonalizada.stream().map(questao -> toDto(questao)).collect(Collectors.toList());
    }
}
