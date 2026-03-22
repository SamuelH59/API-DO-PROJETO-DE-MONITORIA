package com.example.demoTcc.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Monitor {

    @Id
    private int RA;
    @Column
    private String nome;
    @Column
    private String curso;
    @Column
    private String Email;
    @Column
    private String Senha;
    @Column
    private String HorariosDispo;

    public int getRA() {
        return RA;
    }

    public void setRA(int RA) {
        this.RA = RA;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getHorariosDispo() {
        return HorariosDispo;
    }

    public void setHorariosDispo(String horariosDispo) {
        HorariosDispo = horariosDispo;
    }


}
