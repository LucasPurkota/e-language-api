package com.tcc.e_language_api.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AlunoDto extends UsuarioDto {
  private Boolean testeRealizado;
  private Double pontosRanking;
}
