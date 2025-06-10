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
        return "reportagem";
    }

    @PostMapping(value = "/reportagem")
    public String cadastrarReportagem(Reportagem reportagem) {
        if (reportagem.getAutor() != null && reportagem.getAutor().getId() != null) {
            reportagem.setAutor(jornalistaServico.listarTodos()
                    .stream()
                    .filter(j -> j.getId().equals(reportagem.getAutor().getId()))
                    .findFirst()
                    .orElse(null));
        }
        if (reportagem.getAssunto() != null && reportagem.getAssunto().getId() != null) {
            reportagem.setAssunto(assuntoServico.listarTodos()
                    .stream()
                    .filter(a -> a.getId().equals(reportagem.getAssunto().getId()))
                    .findFirst()
                    .orElse(null));
        }

        if (reportagem.getId() == null || reportagem.getId().isEmpty()) {
            reportagem.setId(null); // Ensure it's treated as new
            reportagem.setDataCriacao(java.time.LocalDateTime.now());
        }
        reportagemServico.cadastrar(reportagem);
        return "redirect:/";
    }
    @GetMapping(value = "/reportagens")
    public String exibirPaginaListarReportagem(Model model) {
        List<Reportagem> reportagem = reportagemServico.listarTodos();
        model.addAttribute("reportagens", reportagem);
        return "reportagens";
    }

    @GetMapping(value = "/removerReportagem")
    public String removerDocumentos(@RequestParam String idReportagem) {
        reportagemServico.remover(idReportagem);
        return "index";
    }

}
