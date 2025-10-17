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
@Table(name = "questao_personalizada")
public class QuestaoPersonalizada {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "questao_personalizada_id", nullable = false, unique = true)
    private UUID questaoPersonalizadaId;
    @ManyToOne
    @JoinColumn(name = "idioma_id", nullable = false,
            foreignKey = @ForeignKey(name = "idioma_id",
                    foreignKeyDefinition = "FOREIGN KEY (idioma_id) REFERENCES idioma (idioma_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Idioma idioma;
    @ManyToOne
    @JoinColumn(name = "aluno_professor_id", nullable = false,
            foreignKey = @ForeignKey(name = "questao_personalizada_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (aluno_professor_id) REFERENCES aluno_professor (aluno_professor_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private AlunoProfessor alunoProfessor;
    @ManyToOne
    @JoinColumn(name = "nivel_dificuldade_id", nullable = false,
            foreignKey = @ForeignKey(name = "questao_personalizada_fk3",
                    foreignKeyDefinition = "FOREIGN KEY (nivel_dificuldade_id) REFERENCES nivel_dificuldade (nivel_dificuldade_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private NivelDificuldade nivelDificuldade;
    @Column(name = "enunciado", nullable = false)
    private String enunciado;
    @ManyToOne
    @JoinColumn(name = "tipo_questao_id", nullable = false,
            foreignKey = @ForeignKey(name = "questao_personalizada_fk4",
                    foreignKeyDefinition = "FOREIGN KEY (tipo_questao_id) REFERENCES tipo_questao (tipo_questao_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private TipoQuestao tipoQuestao;
    @Column(name = "gabarito", nullable = false)
    private String gabarito;
    @Column(name = "resposta")
    private String resposta;
}
