package com.tcc.e_language_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("ALUNO") 
public class Aluno extends Usuario {
  @Column(name = "teste_realizado")
  private Boolean testeRealizado;
  @Column(name = "pontos_ranking")
  private Double pontosRanking;
}
