package com.tcc.e_language_api.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoDto {
  private String tipo;
  private String cep;
  private String logradouro;
  private String bairro;
  private String cidade;
  private String uf;
  private String pais;
  private String complemento;
  private int numero;
}
