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
@Table(name = "aula")
public class Aula {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "aula_id", nullable = false, unique = true)
    private UUID aulaId;
    @ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false,
            foreignKey = @ForeignKey(name = "aula_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (unidade_id) REFERENCES unidade (unidade_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Unidade unidade;
    @Column(name = "numero", nullable = false, unique = true)
    private int numero;
    @Column(name = "titulo", nullable = false, unique = true)
    private String titulo;
    @Column(name = "conteudo", nullable = false, unique = true)
    private String conteudo;
    @Column(name = "link_video", nullable = false, unique = true)
    private String linkVideo;
}