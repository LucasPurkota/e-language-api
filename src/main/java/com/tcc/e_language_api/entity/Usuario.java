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
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario implements Serializable{
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
  // @Column(name = "tipo", nullable = false)
  // private Tipo tipo;
  @Column(name = "data_nascimento", nullable = false)
  private LocalDate dataNascimento;
  @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
  private List<Endereco> enderecos;
  

  public enum Tipo {
    ADMIN, PROFESSOR, ALUNO
  }  
}