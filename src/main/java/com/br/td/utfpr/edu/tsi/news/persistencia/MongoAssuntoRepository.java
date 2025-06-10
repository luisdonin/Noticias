package com.br.td.utfpr.edu.tsi.news.persistencia;

import com.br.td.utfpr.edu.tsi.news.modelo.Assunto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoAssuntoRepository extends MongoRepository<Assunto, String> {
}
