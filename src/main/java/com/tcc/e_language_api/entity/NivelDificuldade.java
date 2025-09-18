package com.tcc.e_language_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "nivel_dificuldade")
public class NivelDificuldade {
    @Id
    @Column(name = "nivel_dificuldade_id", nullable = false, unique = true)
    private int nivelDificuldadeId;
    @Column(name = "descricao", nullable = false)
    private String descricao;
}
