package com.tcc.e_language_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("ALUNO") 
public class Aluno extends Usuario {
  @Column(name = "teste_realizado", nullable = false)
  private Boolean testeRealizado;
  @Column(name = "pontos_ranking", nullable = false)
  private Double pontosRanking;
}
