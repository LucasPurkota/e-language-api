package com.tcc.e_language_api.entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "nivelamento_questao_aula")
public class NivelamentoQuestaoAula {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "nivelamento_questao_aula_id", nullable = false, unique = true)
    private UUID nivelamentoQuestaoAulaid;
    @ManyToOne
    @JoinColumn(name = "nivelamento_id", nullable = false,
            foreignKey = @ForeignKey(name = "nivelamento_questao_aula_fk",
                    foreignKeyDefinition = "FOREIGN KEY (nivelamento_id) REFERENCES nivelamento (nivelamento_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Nivelamento nivelamento;
    @ManyToOne
    @JoinColumn(name = "questao_aula_id", nullable = false,
            foreignKey = @ForeignKey(name = "nivelamento_questao_aula_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (questao_aula_id) REFERENCES questao_aula (questao_aula_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private QuestaoAula questaoAula;
    @Column(name = "resposta")
    private String resposta;
    @Column(name = "correto", length = 1)
    private String correto;
}
