package com.br.td.utfpr.edu.tsi.news.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Reportagem {
    @Id
    private String id;
    private String reportTitulo;
    private String reportConteudo;
    private Jornalista autor;
    private Assunto assunto;
    LocalDateTime dataCriacao;


    public Reportagem() {
    }
    public Reportagem(String reportTitulo, String reportConteudo, Jornalista autor, Assunto assunto) {
        this.reportTitulo = reportTitulo;
        this.reportConteudo = reportConteudo;
        this.autor = autor;
        this.assunto = assunto;
        this.dataCriacao = LocalDateTime.now();

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReportTitulo() {
        return reportTitulo;
    }

    public void setReportTitulo(String reportTitulo) {
        this.reportTitulo = reportTitulo;
    }

    public String getReportConteudo() {
        return reportConteudo;
    }

    public void setReportConteudo(String reportConteudo) {
        this.reportConteudo = reportConteudo;
    }

    public Jornalista getAutor() {
        return autor;
    }

    public void setAutor(Jornalista autor) {
        this.autor = autor;
    }

    public Assunto getAssunto() {
        return assunto;
    }

    public void setAssunto(Assunto assunto) {
        this.assunto = assunto;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return "Reportagem{" +
                "reportTitulo='" + reportTitulo + '\'' +
                ", reportConteudo='" + reportConteudo + '\'' +
                ", autor=" + autor +
                ", assunto=" + assunto +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
