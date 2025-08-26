package com.tcc.e_language_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("PROFESSOR") 
public class Professor extends Usuario{
  @Column(name = "aprovado")
  private Boolean aprovado;
}
