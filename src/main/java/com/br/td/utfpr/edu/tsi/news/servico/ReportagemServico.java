package com.br.td.utfpr.edu.tsi.news.servico;

import com.br.td.utfpr.edu.tsi.news.modelo.Reportagem;

import java.util.List;

public interface ReportagemServico {
    public  void cadastrar(Reportagem Reportagem);
    List<Reportagem> listarTodos();
    public void remover(String idReportagem);
}


