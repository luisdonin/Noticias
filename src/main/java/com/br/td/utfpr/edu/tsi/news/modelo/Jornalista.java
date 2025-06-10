package com.br.td.utfpr.edu.tsi.news.modelo;

/* June, 9th, 2025*/
/*One question I've had this whole semester is:
* Are the parts that make my Jornalista classes themselves?
* */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Jornalista {
    @Id
    private String id;
    private String nome;
    private String email;


    public Jornalista(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Jornalista() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Jornalista{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

