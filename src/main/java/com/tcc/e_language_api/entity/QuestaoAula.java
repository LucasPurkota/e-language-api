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
@Table(name = "questao_aula")
public class QuestaoAula {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "questao_aula_id", nullable = false, unique = true)
    private UUID questaoAulaId;
    @ManyToOne
    @JoinColumn(name = "aula_id", nullable = false,
            foreignKey = @ForeignKey(name = "questao_aula_fk",
                    foreignKeyDefinition = "FOREIGN KEY (aula_id) REFERENCES aula (aula_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Aula aula;
    @ManyToOne
    @JoinColumn(name = "nivel_dificuldade_id", nullable = false,
            foreignKey = @ForeignKey(name = "questao_aula_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (nivel_dificuldade_id) REFERENCES nivel_dificuldade (nivel_dificuldade_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private NivelDificuldade nivelDificuldade;
    @Column(name = "enunciado", nullable = false)
    private String enunciado;
    @ManyToOne
    @JoinColumn(name = "tipo_questao_id", nullable = false,
            foreignKey = @ForeignKey(name = "questao_aula_fk3",
                    foreignKeyDefinition = "FOREIGN KEY (tipo_questao_id) REFERENCES tipo_questao (tipo_questao_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private TipoQuestao tipoQuestao;
    @Column(name = "gabarito")
    private UUID gabarito;
}
