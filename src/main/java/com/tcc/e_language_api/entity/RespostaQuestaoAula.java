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
@Table(name = "resposta_questao_aula")
public class RespostaQuestaoAula {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "resposta_questao_aula_id", nullable = false, unique = true)
    private UUID respostaQuestaoAulaId;
    @ManyToOne
    @JoinColumn(name = "aluno_unidade_id", nullable = false,
            foreignKey = @ForeignKey(name = "resposta_questao_aula_fk",
                    foreignKeyDefinition = "FOREIGN KEY (aluno_unidade_id) REFERENCES aluno_unidade (aluno_unidade_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private AlunoUnidade alunoUnidade;
    @ManyToOne
    @JoinColumn(name = "questao_aula_id", nullable = false,
            foreignKey = @ForeignKey(name = "resposta_questao_aula_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (questao_aula_id) REFERENCES questao_aula (questao_aula_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private QuestaoAula questaoAula;
    @Column(name = "resposta", nullable = false)
    private String resposta;
}
