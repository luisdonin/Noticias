package com.br.td.utfpr.edu.tsi.news.servico.serviceImp;

import com.br.td.utfpr.edu.tsi.news.modelo.Reportagem;
import com.br.td.utfpr.edu.tsi.news.persistencia.MongoReportagemRepository;
import com.br.td.utfpr.edu.tsi.news.servico.ReportagemServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReportagemServicoImp implements ReportagemServico {
    @Autowired
    private MongoReportagemRepository reportagemRepository;

    @Override
    public void cadastrar(Reportagem reportagem) {
        reportagemRepository.save(reportagem);
    }

    @Override
    public List<Reportagem> listarTodos() {
        return reportagemRepository.findAll();
    }

    @Override
    public void remover(String idReportagem) {
        reportagemRepository.deleteById(idReportagem);
    }
}
