package com.tcc.e_language_api.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class DesafioDiarioRespostaDto {
    private UUID desafioDiarioId;
    private String resposta;
    private String correto;
}
