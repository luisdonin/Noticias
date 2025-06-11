package com.br.td.utfpr.edu.tsi.news.servico;

import com.br.td.utfpr.edu.tsi.news.modelo.Reportagem;

import java.time.LocalDate;
import java.util.List;

public interface ReportagemServico {
    public  void cadastrar(Reportagem Reportagem);
    List<Reportagem> listarTodos();
    public void remover(String idReportagem);

    boolean canPostReportagem(String id, String id1, LocalDate localDate);
}


