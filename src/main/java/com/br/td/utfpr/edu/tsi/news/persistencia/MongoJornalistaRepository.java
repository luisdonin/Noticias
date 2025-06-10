package com.br.td.utfpr.edu.tsi.news.persistencia;

import com.br.td.utfpr.edu.tsi.news.modelo.Jornalista;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoJornalistaRepository extends MongoRepository<Jornalista, String> {
}
