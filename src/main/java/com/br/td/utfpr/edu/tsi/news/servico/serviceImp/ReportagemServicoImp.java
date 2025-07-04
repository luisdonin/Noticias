package com.br.td.utfpr.edu.tsi.news.servico.serviceImp;

import com.br.td.utfpr.edu.tsi.news.modelo.Reportagem;
import com.br.td.utfpr.edu.tsi.news.persistencia.MongoReportagemRepository;
import com.br.td.utfpr.edu.tsi.news.servico.ReportagemServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Service
public class ReportagemServicoImp implements ReportagemServico {
    @Autowired
    private MongoReportagemRepository reportagemRepository;

    @Override
    public Reportagem cadastrar(Reportagem reportagem) {
        return reportagemRepository.save(reportagem);
    }

    @Override
    public List<Reportagem> listarTodos() {
        return reportagemRepository.findAll();
    }

    @Override
    public void remover(String idReportagem) {
        reportagemRepository.deleteById(idReportagem);

    }
    /*June 10th, 2025*/
    /*Check if a new Reportagem can be posted*/
    public boolean canPostReportagem(String autorId, String assuntoId, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        return reportagemRepository.countByAutorIdAndAssuntoIdAndDataCriacaoBetween(
                autorId, assuntoId, start, end
        ) < 2;
    }
}
