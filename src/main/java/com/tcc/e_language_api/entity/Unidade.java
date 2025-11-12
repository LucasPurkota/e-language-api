package com.tcc.e_language_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "unidade")
public class Unidade {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "unidade_id", nullable = false, unique = true)
    private UUID unidadeId;
    @ManyToOne
    @JoinColumn(name = "idioma_id", nullable = false,
            foreignKey = @ForeignKey(name = "unidade_fk",
                    foreignKeyDefinition = "FOREIGN KEY (idioma_id) REFERENCES idioma (idioma_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Idioma idioma;
    @ManyToOne
    @JoinColumn(name = "nivel_idioma_id", nullable = false,
            foreignKey = @ForeignKey(name = "unidade_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (nivel_idioma_id) REFERENCES nivel_idioma (nivel_idioma_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private NivelIdioma nivelIdioma;
    @Column(name = "numero", nullable = false)
    private int numero;
    @Column(name = "titulo", nullable = false)
    private String titulo;
    @Column(name = "conteudo", nullable = false)
    private String conteudo;
}
