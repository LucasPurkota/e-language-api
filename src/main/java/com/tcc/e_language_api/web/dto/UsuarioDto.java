package com.tcc.e_language_api.web.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDto {
  private String nome;
  private String email;
  private String senha;
  private String tipo;
  private LocalDate dataNascimento;

  private Boolean testeRealizado;
  private Double pontosRanking;

  private Double aprovado;

  private List<EnderecoDto> enderecos;
}
