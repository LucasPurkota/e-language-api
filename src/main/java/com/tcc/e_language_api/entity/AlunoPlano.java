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
@Table(name = "aluno_plano")
public class AlunoPlano {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "aluno_plano_id", nullable = false, unique = true)
    private UUID alunoPlanoId;
    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false,
            foreignKey = @ForeignKey(name = "aluno_palno_fk",
                    foreignKeyDefinition = "FOREIGN KEY (aluno_id) REFERENCES perfil (perfil_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Perfil aluno;
    @ManyToOne
    @JoinColumn(name = "plano_id", nullable = false,
            foreignKey = @ForeignKey(name = "aluno_plano_fk2",
                    foreignKeyDefinition = "FOREIGN KEY (plano_id) REFERENCES plano (plano_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private Plano plano;
    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id", nullable = false,
            foreignKey = @ForeignKey(name = "aluno_plano_fk3",
                    foreignKeyDefinition = "FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento (forma_pagamento_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private FormaPagamento formaPagamento;
    @Column(name = "numero_cartao", nullable = false)
    private String numeroCartao;
    @Column(name = "vencimento_cartao", nullable = false)
    private String vencimentoCartao;
    @Column(name = "cvc", nullable = false)
    private String cvc;
    @Column(name = "nome_cartao", nullable = false)
    private String nomeCartao;
}
