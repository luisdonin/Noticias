package com.br.td.utfpr.edu.tsi.news.controle;


import com.br.td.utfpr.edu.tsi.news.modelo.Jornalista;
import com.br.td.utfpr.edu.tsi.news.servico.JornalistaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class controleJornalista {
    @Autowired
    private JornalistaServico jornalistaServico;

    @GetMapping("/paineldecontrole")
    public String exibirPaginaCadastrarJornalista(@RequestParam(required = false) String idJornalista, Model model) {
        Jornalista jornalista = null;
        if (idJornalista != null) {
            jornalista = jornalistaServico.listarTodos()
                    .stream()
                    .filter(a -> a.getId().equals(idJornalista))
                    .findFirst()
                    .orElse(null);
        }
        if (jornalista == null) {
            jornalista = new Jornalista(null, "");
        }
        model.addAttribute("jornalista", jornalista);
        return "paineldecontrole";
    }

    @PostMapping(value = "/jornalista")
    public String cadastrarJornalista(Jornalista jornalista) {
        if (jornalista.getId() == null || jornalista.getId().isEmpty()) {
            jornalista.setId(null);
        }
        jornalistaServico.cadastrar(jornalista);
        return "redirect:/";
    }

    @GetMapping(value = "/jornalistas")
    public String exibirPaginaListarJornalista(Model model) {
        List<Jornalista> jornalistas = jornalistaServico.listarTodos();
        model.addAttribute("jornalistas", jornalistas);
        return "jornalistas";
    }

    @GetMapping(value = "/removerJornalista")
    public String removerJornalista(@RequestParam String idJornalista) {
        jornalistaServico.remover(idJornalista);
        return "redirect:/jornalista";
    }


}
