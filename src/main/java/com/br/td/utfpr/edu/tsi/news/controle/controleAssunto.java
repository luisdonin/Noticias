package com.br.td.utfpr.edu.tsi.news.controle;


import com.br.td.utfpr.edu.tsi.news.modelo.Assunto;
import com.br.td.utfpr.edu.tsi.news.servico.AssuntoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class controleAssunto {

    @Autowired
    private AssuntoServico assuntoServico;

    @GetMapping("/assunto")
    public String exibirPaginaCadastrarAssunto(@RequestParam(required = false) String idAssunto, Model model) {
        Assunto assunto = null;
        if (idAssunto != null) {
            assunto = assuntoServico.listarTodos()
                    .stream()
                    .filter(a -> a.getId().equals(idAssunto))
                    .findFirst()
                    .orElse(null);
        }
        if (assunto == null) {
            assunto = new Assunto();
        }
        model.addAttribute("assunto", assunto);
        return "assunto";
    }

    @PostMapping(value = "/assunto")
    public String cadastrarAssunto(Assunto assunto) {
        if (assunto.getId() == null || assunto.getId().isEmpty()) {
            assunto.setId(null);
        }
        assuntoServico.cadastrar(assunto);
        return "redirect:/assunto";
    }

    @GetMapping(value = "/assuntos")
    public String exibirPaginaListarAssunto(Model model) {
        List<Assunto> assuntos = assuntoServico.listarTodos();
        model.addAttribute("assuntos", assuntos);
        return "assuntos";
    }



    @GetMapping(value = "/removerAssunto")
    public String removerAssunto(@RequestParam String idAssunto) {
        assuntoServico.remover(idAssunto);
        return "redirect:/assuntos";
    }


}
