package com.br.td.utfpr.edu.tsi.news.servico.serviceImp;

import com.br.td.utfpr.edu.tsi.news.modelo.Assunto;
import com.br.td.utfpr.edu.tsi.news.persistencia.MongoAssuntoRepository;
import com.br.td.utfpr.edu.tsi.news.servico.AssuntoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*June 9th, 2025*/
/*The issue is that I wasn't passing an Assunto object as a parameter when registering.*/
/*I did the same thing with reportagem, but forgot to document.*/
@Service
public class AssuntoServicoImp implements AssuntoServico {

    @Autowired
    private MongoAssuntoRepository assuntoRepository; // replace by assuntoDAO

    /*
    * @Autowired
    * private AssuntoDAO assuntoDAO; // replace by assuntoRepository
    * */
    @Override
    public void cadastrar(Assunto assunto) {
        assuntoRepository.save(assunto);
    }

    @Override
    public List<Assunto> listarTodos() {
        return assuntoRepository.findAll();
    }

    @Override
    public void remover(String idAssunto) {
        assuntoRepository.deleteById(idAssunto);
    }

}

