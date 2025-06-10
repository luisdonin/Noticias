package com.br.td.utfpr.edu.tsi.news.persistencia;

import com.br.td.utfpr.edu.tsi.news.modelo.Reportagem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoReportagemRepository extends MongoRepository<Reportagem, String> {
}
