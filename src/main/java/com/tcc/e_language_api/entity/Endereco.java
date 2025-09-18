package com.tcc.e_language_api.entity;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Endereco")
public class Endereco {
  @Id
  @GeneratedValue(generator = "uuid_generate_v4()")
  @Column(name = "endereco_id", nullable = false, unique = true)
  private UUID enderecoId;
  @ManyToOne
  @JoinColumn(name = "usuario_id", nullable = false, 
  foreignKey = @ForeignKey(name = "endereco_fk",
  foreignKeyDefinition = "FOREIGN KEY (usuario_id) REFERENCES usuario (usuario_id) ON DELETE CASCADE ON UPDATE CASCADE"
))
  private Usuario usuario;
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo", nullable = false)
  private Tipo tipo;
  @Column(name = "cep", nullable = false)
  private String cep;
  @Column(name = "logradouro", nullable = false)
  private String logradouro;
  @Column(name = "bairro", nullable = false)
  private String bairro;
  @Column(name = "cidade", nullable = false)
  private String cidade;
  @Column(name = "uf", nullable = false)
  private String uf;
  @Column(name = "pais", nullable = false)
  private String pais;
  @Column(name = "complemento", nullable = false)
  private String complemento;
  @Column(name = "numero", nullable = false)
  private int numero;

  public enum Tipo {
    RESIDENCIAL, COMERCIAL
  }
}
