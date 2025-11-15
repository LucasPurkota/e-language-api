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
@Table(name = "perfil_idioma")
public class PerfilIdioma {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "perfil_idioma_id", nullable = false, unique = true)
    private UUID perfilIdiomaId;
    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false,
            foreignKey = @ForeignKey(name = "perfil_idioma_fk",
                    foreignKeyDefinition = "FOREIGN KEY (perfil_id) REFERENCES perfil (perfil_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Perfil perfil;
    @ManyToOne
    @JoinColumn(name = "idioma_id", nullable = false,
            foreignKey = @ForeignKey(name = "perfil_idioma_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (idioma_id) REFERENCES idioma (idioma_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Idioma idioma;
    @ManyToOne
    @JoinColumn(name = "nivel_idioma_id",
            foreignKey = @ForeignKey(name = "qperfil_idioma_fk3",
                    foreignKeyDefinition = "FOREIGN KEY (nivel_idioma_id) REFERENCES nivel_idioma (nivel_idioma_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private NivelIdioma nivelIdioma;
    @Column(name = "pontos_ranking", nullable = false)
    private Double pontosRanking;
}
