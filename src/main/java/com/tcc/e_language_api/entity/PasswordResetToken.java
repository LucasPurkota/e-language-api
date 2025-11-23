package com.tcc.e_language_api.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Instant expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private com.tcc.e_language_api.entity.Usuario user;

    public PasswordResetToken() {}

    public PasswordResetToken(String token, com.tcc.e_language_api.entity.Usuario user, double hoursToExpire) {
        this.token = token;
        this.user = user;
        long minutesToExpire = (long) (hoursToExpire * 60);
        this.expiryDate = Instant.now().plus(minutesToExpire, ChronoUnit.MINUTES);
    }

    // getters/setters
    public Long getId() { return id; }
    public String getToken() { return token; }
    public Instant getExpiryDate() { return expiryDate; }
    public com.tcc.e_language_api.entity.Usuario getUser() { return user; }
    public void setId(Long id) { this.id = id; }
    public void setToken(String token) { this.token = token; }
    public void setExpiryDate(Instant expiryDate) { this.expiryDate = expiryDate; }
    public void setUser(com.tcc.e_language_api.entity.Usuario user) { this.user = user; }
}