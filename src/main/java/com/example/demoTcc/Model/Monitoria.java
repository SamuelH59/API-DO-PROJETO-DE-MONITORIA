package com.example.demoTcc.Model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Monitoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column
    private String emailAluno;
    @Column
    private String emailMonitor;
    @Column
    private String Horario;
    @Column
    private String Assunto;
    @Column
    private String Modo;
    @Column
    private String Relatorio;
    @Column
    private String situacao;

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    public void setEmailAluno(String emailAluno) {
        this.emailAluno = emailAluno;
    }

    public String getEmailMonitor() {
        return emailMonitor;
    }

    public void setEmailMonitor(String emailMonitor) {
        this.emailMonitor = emailMonitor;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }

    public String getAssunto() {
        return Assunto;
    }

    public void setAssunto(String assunto) {
        Assunto = assunto;
    }

    public String getModo() {
        return Modo;
    }

    public void setModo(String modo) {
        Modo = modo;
    }

    public String getRelatorio() {
        return Relatorio;
    }

    public void setRelatorio(String relatorio) {
        Relatorio = relatorio;
    }
}
