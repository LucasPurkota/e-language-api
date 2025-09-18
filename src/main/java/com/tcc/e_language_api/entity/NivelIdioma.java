package com.tcc.e_language_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "nivel_idioma")
public class NivelIdioma {
    @Id
    @Column(name = "nivel_idioma_id", nullable = false, unique = true)
    private int nivelIdiomaId;
    @Column(name = "descricao", nullable = false)
    private String descricao;
}
