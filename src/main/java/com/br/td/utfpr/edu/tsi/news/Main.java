package com.br.td.utfpr.edu.tsi.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
/*@PropertySource({ "file:./application.properties" })
@ImportResource({ "file:./applicationContext.xml" })*/
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.setProperty("server.servlet.context-path", "/news");
    }

}
