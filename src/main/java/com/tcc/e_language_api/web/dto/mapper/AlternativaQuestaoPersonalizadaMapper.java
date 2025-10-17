package com.tcc.e_language_api.web.dto.mapper;

import com.tcc.e_language_api.entity.AlternativaQuestaoPersonalizada;
import com.tcc.e_language_api.entity.QuestaoPersonalizada;
import com.tcc.e_language_api.web.dto.AlternativaQuestaoPersonalizadaDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class AlternativaQuestaoPersonalizadaMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static AlternativaQuestaoPersonalizada toEntity(AlternativaQuestaoPersonalizadaDto dto) {

        QuestaoPersonalizada questaoPersonalizada = new QuestaoPersonalizada();
        questaoPersonalizada.setQuestaoPersonalizadaId(dto.getQuestaoPersonalizadaId());

        AlternativaQuestaoPersonalizada alternativaQuestaoPersonalizada = new AlternativaQuestaoPersonalizada();
        alternativaQuestaoPersonalizada.setQuestaoPersonalizada(questaoPersonalizada);
        alternativaQuestaoPersonalizada.setAlternativa(dto.getAlternativa());
        alternativaQuestaoPersonalizada.setDescricao(dto.getDescricao());
        return alternativaQuestaoPersonalizada;
    }
}
