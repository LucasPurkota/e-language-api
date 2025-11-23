package com.tcc.e_language_api.web.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoPlanoDto {
    private UUID alunoPlanoId;
    private UUID alunoId;
    private UUID planoId;
    private String tituloPlano;
    private int formaPagamentoId;
    private String formaPagamentoDescricao;
    private String numeroCartao;
    private String vencimentoCartao;
    private String cvc;
    private String nomeCartao;
}
