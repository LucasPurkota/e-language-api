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
@Table(name = "alternativa_questao_personalizada")
public class AlternativaQuestaoPersonalizada {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "alternativa_questao_personalizada_id", nullable = false, unique = true)
    private UUID alternativaQuestaoPersonalizadaId;
    @ManyToOne
    @JoinColumn(name = "questao_personalizada_id", nullable = false,
            foreignKey = @ForeignKey(name = "alternativa_questao_personalizada_fk",
                    foreignKeyDefinition = "FOREIGN KEY (questao_personalizada_id) REFERENCES questao_personalizada (questao_personalizada_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private QuestaoPersonalizada questaoPersonalizada;
    @Column(name = "alternativa", nullable = false)
    private String alternativa;
    @Column(name = "descricao", nullable = false)
    private String descricao;
}
