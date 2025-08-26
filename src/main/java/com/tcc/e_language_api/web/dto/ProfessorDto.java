package com.tcc.e_language_api.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ProfessorDto extends UsuarioDto {
  private Boolean aprovado;
}
