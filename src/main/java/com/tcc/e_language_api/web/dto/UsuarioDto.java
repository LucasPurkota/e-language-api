package com.tcc.e_language_api.web.dto;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDto {
  private UUID usuarioId;
  private String nome;
  @NotBlank
  @CPF
  private String cpf;
  @NotBlank
  @Email(message = "formato do e-mail está invalido", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
  private String email;
  @NotBlank
  @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
//  @Pattern(
//          regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
//          message = "Senha deve conter pelo menos 1 letra maiúscula, 1 minúscula, 1 número e 1 caractere especial"
//  )
  private String senha;

  private List<EnderecoDto> enderecos;
  private List<PerfilDto> perfil;

}
