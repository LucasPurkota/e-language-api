package com.tcc.e_language_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(name = "status_id", nullable = false, unique = true)
    private int statusId;
    @Column(name = "descricao", nullable = false)
    private String descricao;
}
