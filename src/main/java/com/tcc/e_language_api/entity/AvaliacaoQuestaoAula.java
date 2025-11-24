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
@Table(name = "avaliacao_questao_aula")
public class AvaliacaoQuestaoAula {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "avaliacao_questao_aula_id", nullable = false, unique = true)
    private UUID avaliacaoQuestaoAulaId;
    @ManyToOne
    @JoinColumn(name = "avaliacao_id", nullable = false,
            foreignKey = @ForeignKey(name = "questao_aula_fk",
                    foreignKeyDefinition = "FOREIGN KEY (avaliacao_id) REFERENCES avaliacao (avaliacao_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Avaliacao avaliacao;
    @ManyToOne
    @JoinColumn(name = "questao_aula_id", nullable = false,
            foreignKey = @ForeignKey(name = "questao_aula_fk",
                    foreignKeyDefinition = "FOREIGN KEY (questao_aula_id) REFERENCES questao_aula (questao_aula_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private QuestaoAula questaoAula;
    @Column(name = "resposta")
    private String resposta;
    @Column(name = "correto")
    private String correto;
}
