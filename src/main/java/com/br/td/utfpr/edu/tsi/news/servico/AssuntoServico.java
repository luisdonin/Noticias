package com.br.td.utfpr.edu.tsi.news.servico;

import com.br.td.utfpr.edu.tsi.news.modelo.Assunto;

import java.util.List;

public interface AssuntoServico {
    public void cadastrar(Assunto assunto);
    public List<Assunto> listarTodos();
    public void remover(String idAssunto);
}
