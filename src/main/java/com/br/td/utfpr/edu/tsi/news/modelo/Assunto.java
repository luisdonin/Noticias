package com.br.td.utfpr.edu.tsi.news.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
*
* June 9th, 2025
*
* yesterday I refactored most of the code, and rebuilt the structure
* today I'm implementing all the changes that can be found at Documentação.pdf
* first I'm writing the classes, but only declaring the variables, for now.
*
* */
@Document
public class Assunto {
    @Id
    private String id;
    private String assuntoTitulo;

    public Assunto(String id, String assuntoTitulo) {
        this.id = id;
        this.assuntoTitulo = assuntoTitulo;
    }

    public Assunto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssuntoTitulo() {
        return assuntoTitulo;
    }

    public void setAssuntoTitulo(String assuntoTitulo) {
        this.assuntoTitulo = assuntoTitulo;
    }

    @Override
    public String toString() {
        return "Assunto{" +
                "id='" + id + '\'' +
                ", assuntoTitulo='" + assuntoTitulo + '\'' +
                '}';
    }
}



