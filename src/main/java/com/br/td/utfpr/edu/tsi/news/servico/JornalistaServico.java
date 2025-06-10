package com.br.td.utfpr.edu.tsi.news.servico;

import com.br.td.utfpr.edu.tsi.news.modelo.Jornalista;

import java.util.List;
/*June, 9th, 2025*/
/*Implementing Service*/
public interface JornalistaServico {
    public void cadastrar(Jornalista jornalista);
    public List<Jornalista> listarTodos();
    public void remover(String idJornalista);
}


