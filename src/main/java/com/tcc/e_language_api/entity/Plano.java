package com.tcc.e_language_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.Normalizer;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "plano")
public class Plano {
    @Id
    @GeneratedValue(generator = "uuid_generate_v4()")
    @Column(name = "plano_id", nullable = false, unique = true)
    private UUID planoId;
    @Column(name = "titulo", nullable = false)
    private String titulo;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id", nullable = false,
            foreignKey = @ForeignKey(name = "plano_fk",
                    foreignKeyDefinition = "FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento (forma_pagamento_id) ON DELETE CASCADE ON UPDATE CASCADE"
            ))
    private FormaPagamento formaPagamento;
}
