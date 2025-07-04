package com.br.td.utfpr.edu.tsi.news;

import com.br.td.utfpr.edu.tsi.news.modelo.Reportagem;
import com.br.td.utfpr.edu.tsi.news.servico.Indexador;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import com.br.td.utfpr.edu.tsi.news.modelo.Reportagem;
import com.br.td.utfpr.edu.tsi.news.servico.ReportagemServico;



import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
/*@PropertySource({ "/application.properties" })
@ImportResource({ "applicationContext.xml" })*/
public class Main {

    private final ReportagemServico reportagemServico;
    private final Indexador indexador;

    public Main(ReportagemServico reportagemServico, Indexador indexador) {
        this.reportagemServico = reportagemServico;
        this.indexador = indexador;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.setProperty("server.servlet.context-path", "/news");
    }

    @PostConstruct
    public void indexarNoticias() {
        List<Reportagem> reportagem = reportagemServico.listarTodos();
        if (reportagem == null || reportagem.isEmpty()) {
            System.out.println("indexador vazio");
        }
        indexador.indexar(reportagem);
    }

}
