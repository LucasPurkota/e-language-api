package com.tcc.e_language_api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoUnidadeDto {
    private UUID alunoUnidadeId;
    private UUID alunoId;
    private UnidadeDto unidade;
    private int statusId;
    private String statusDescricao;
}
