package com.br.td.utfpr.edu.tsi.news.persistencia;

import com.br.td.utfpr.edu.tsi.news.modelo.Reportagem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
/*June 10th, 2025*/
/*To limit how many Reportagens can be saved in a day*/
public interface MongoReportagemRepository extends MongoRepository<Reportagem, String> {
    long countByAutorIdAndAssuntoIdAndDataCriacaoBetween(
            String autorId, String assuntoId, LocalDateTime start, LocalDateTime end
    );
}
