package com.tcc.e_language_api.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "nivelamento")
public class Nivelamento {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "nivelamento_id", nullable = false, unique = true)
    private UUID nivelamentoId;
    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false,
            foreignKey = @ForeignKey(name = "nivelamento_fk",
                    foreignKeyDefinition = "FOREIGN KEY (perfil_id) REFERENCES perfil (perfil_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Perfil perfil;
    @ManyToOne
    @JoinColumn(name = "idioma_id", nullable = false,
            foreignKey = @ForeignKey(name = "nivelamento_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (idioma_id) REFERENCES idioma (idioma_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Idioma idioma;
    @Column(name = "data_realizacao")
    private LocalDateTime dataRealizacao;
    @Column(name = "nota")
    private Double nota;
    @ManyToOne
    @JoinColumn(name = "nivel_idioma_id",
            foreignKey = @ForeignKey(name = "nivelamento_fk3",
                    foreignKeyDefinition = "FOREIGN KEY (nivel_idioma_id) REFERENCES nivel_idioma (nivel_idioma_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private NivelIdioma nivelIdioma;
    @OneToMany(mappedBy = "nivelamento",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NivelamentoQuestaoAula> nivelamentoQuestaoAulas;
}
