package com.tcc.e_language_api.web.dto;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.UUID;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public abstract class UsuarioDto {
  private UUID usuarioId;
  private String nome;
  private String cpf;
  private String email;
  private String senha;
  private String tipo;
  private LocalDate dataNascimento;

  private List<EnderecoDto> enderecos;

  public boolean validarCpf() {
    // Remove caracteres não numéricos
    this.cpf = this.cpf.replaceAll("[^0-9]", "");

    // Verifica se tem 11 dígitos ou se é uma sequência repetida (ex.:
    // "11111111111")
    if (this.cpf.length() != 11 || this.cpf.matches("(\\d)\\1{10}")) {
      return false;
    }

    try {
      // Calcula o primeiro dígito verificador
      int soma = 0;
      for (int i = 0; i < 9; i++) {
        soma += Character.getNumericValue(this.cpf.charAt(i)) * (10 - i);
      }
      int digito1 = 11 - (soma % 11);
      if (digito1 > 9)
        digito1 = 0;

      // Calcula o segundo dígito verificador
      soma = 0;
      for (int i = 0; i < 10; i++) {
        soma += Character.getNumericValue(this.cpf.charAt(i)) * (11 - i);
      }
      int digito2 = 11 - (soma % 11);
      if (digito2 > 9)
        digito2 = 0;

      // Verifica se os dígitos calculados coincidem com os informados
      return (Character.getNumericValue(this.cpf.charAt(9)) == digito1)
          && (Character.getNumericValue(this.cpf.charAt(10)) == digito2);

    } catch (InputMismatchException e) {
      return false;
    }
  }

}
