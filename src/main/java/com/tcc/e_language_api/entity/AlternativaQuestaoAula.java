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
@Table(name = "alternativa_questao_aula")
public class AlternativaQuestaoAula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid_generate_v4()")
    @Column(name = "alternativa_questao_aula_id", nullable = false, unique = true)
    private UUID alternativaQuestaoAulaId;
    @ManyToOne
    @JoinColumn(name = "questao_aula_id", nullable = false,
            foreignKey = @ForeignKey(name = "alternativa_questao_aula_fk",
                    foreignKeyDefinition = "FOREIGN KEY (questao_aula_id) REFERENCES questao_aula (questao_aula_id) " +
                            "ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private QuestaoAula questaoAula;
    @Column(name = "alternativa", nullable = false)
    private String alternativa;
    @Column(name = "descricao", nullable = false)
    private String descricao;
}
