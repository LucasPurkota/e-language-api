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
@Table(name = "idioma")
public class Idioma {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "idioma_id", nullable = false, unique = true)
    private UUID idiomaId;
    @Column(name = "nome", nullable = false, unique = true)
    private String nome;
    @Column(name = "sigla", nullable = false, unique = true)
    private String sigla;
}
