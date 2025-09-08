package com.tcc.e_language_api.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable{
  @Id
  @GeneratedValue(generator = "uuid_generate_v4()")
  @Column(name = "usuario_id", nullable = false, unique = true)
  private UUID usuarioId;
  @Column(name = "nome", nullable = false)
  private String nome;
  @Column(name = "cpf", nullable = false)
  private String cpf;
  @Column(name = "email", nullable = false, unique = true)
  private String email;
  @Column(name = "senha", nullable = false)
  private String senha;
  @Column(name = "data_criacao", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDate dataCriacao;
  @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Endereco> enderecos;
  @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Perfil> perfil;
}