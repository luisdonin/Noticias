package com.br.td.utfpr.edu.tsi.news.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controleIndex {
    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
}
