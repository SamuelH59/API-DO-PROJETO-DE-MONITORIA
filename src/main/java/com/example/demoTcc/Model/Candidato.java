package com.example.demoTcc.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Candidato {
    @Id
    private String RA;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String curso;
    @Column
    private String boletim;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRA() {
        return RA;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getBoletim() {
        return boletim;
    }

    public void setBoletim(String boletim) {
        this.boletim = boletim;
    }
}
