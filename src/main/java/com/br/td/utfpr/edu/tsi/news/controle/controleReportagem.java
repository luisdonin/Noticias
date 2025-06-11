package com.br.td.utfpr.edu.tsi.news.controle;


import com.br.td.utfpr.edu.tsi.news.modelo.Assunto;
import com.br.td.utfpr.edu.tsi.news.modelo.Jornalista;
import com.br.td.utfpr.edu.tsi.news.modelo.Reportagem;
import com.br.td.utfpr.edu.tsi.news.servico.AssuntoServico;
import com.br.td.utfpr.edu.tsi.news.servico.JornalistaServico;
import com.br.td.utfpr.edu.tsi.news.servico.ReportagemServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.br.td.utfpr.edu.tsi.news.modelo.ReportagemSituacao;

import java.util.List;

@Controller
public class controleReportagem {


    @Autowired
    private ReportagemServico reportagemServico;

    @Autowired
    private JornalistaServico jornalistaServico;

    @Autowired
    private AssuntoServico assuntoServico;

    @GetMapping("/reportagem")
    public String exibirPaginaCadastrarReportagem(@RequestParam(required = false) String idReportagem, Model model) {
        Reportagem reportagem = null;
        if (idReportagem != null) {
            reportagem = reportagemServico.listarTodos()
                    .stream()
                    .filter(r -> r.getId().equals(idReportagem))
                    .findFirst()
                    .orElse(null);
        }
        if (reportagem == null) {
            reportagem = new Reportagem();
        }

        if (reportagem.getAutor() == null) {
            reportagem.setAutor(new Jornalista());
        }
        if (reportagem.getAssunto() == null) {
            reportagem.setAssunto(new Assunto());
        }
        model.addAttribute("reportagem", reportagem);
        model.addAttribute("jornalistas", jornalistaServico.listarTodos());
        model.addAttribute("assuntos", assuntoServico.listarTodos());
        model.addAttribute("situacao", ReportagemSituacao.values());
        return "reportagem";
    }



    @PostMapping(value = "/reportagem")
    public String cadastrarReportagem(Reportagem reportagem, Model model) {
        if (reportagem.getAutor() != null && reportagem.getAutor().getId() != null) {
            reportagem.setAutor(jornalistaServico.listarTodos()
                    .stream()
                    .filter(j -> j.getId().equals(reportagem.getAutor().getId()))
                    .findFirst()
                    .orElse(null));
        }

        if (reportagem.getId() == null || reportagem.getId().isEmpty()) {
            reportagem.setId(null);
            reportagem.setDataCriacao(java.time.LocalDateTime.now());
        }
        /*June 10th, 2025*/
        /*Here is where I enforced it, hopefully it works*/
        if (!reportagemServico.canPostReportagem(
                reportagem.getAutor().getId(),
                reportagem.getAssunto().getId(),
                reportagem.getDataCriacao().toLocalDate())) {
            model.addAttribute("error", "Limite de 2 reportagens por assunto por dia .");
            model.addAttribute("jornalistas", jornalistaServico.listarTodos());
            model.addAttribute("assuntos", assuntoServico.listarTodos());
            model.addAttribute("situacao", ReportagemSituacao.values());
            return "reportagem";
        }

        if (reportagem.getAssunto() != null && reportagem.getAssunto().getId() != null) {
            reportagem.setAssunto(assuntoServico.listarTodos()
                    .stream()
                    .filter(a -> a.getId().equals(reportagem.getAssunto().getId()))
                    .findFirst()
                    .orElse(null));
        }

        reportagemServico.cadastrar(reportagem);
        return "redirect:/reportagem";
    }


    @GetMapping("/reportagens")
    public String exibirPaginaListarReportagem(Model model) {
        List<Reportagem> naoPublicadas = reportagemServico.listarTodos()
                .stream()
                .filter(r -> r.getSituacao() == ReportagemSituacao.pendente || r.getSituacao() == ReportagemSituacao.cancelada)
                .toList();
        model.addAttribute("reportagens", naoPublicadas);
        return "reportagens";
    }

    @GetMapping(value = "/removerReportagem")
    public String removerDocumentos(@RequestParam String idReportagem) {
        reportagemServico.remover(idReportagem);
        return "redirect:/reportagens";
    }
    @GetMapping("/reportagensPublic")
    public String exibirPaginaListarReportagemPublic(Model model) {
        List<Reportagem> publicadas = reportagemServico.listarTodos()
                .stream()
                .filter(r -> r.getSituacao() == ReportagemSituacao.publicada)
                .toList();
        model.addAttribute("reportagens", publicadas);
        return "reportagensPublic";
    }

}
