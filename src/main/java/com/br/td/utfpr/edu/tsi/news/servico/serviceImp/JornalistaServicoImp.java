package com.br.td.utfpr.edu.tsi.news.servico.serviceImp;

import com.br.td.utfpr.edu.tsi.news.modelo.Jornalista;
import com.br.td.utfpr.edu.tsi.news.persistencia.MongoJornalistaRepository;
import com.br.td.utfpr.edu.tsi.news.servico.JornalistaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JornalistaServicoImp implements JornalistaServico {

    @Autowired
    private MongoJornalistaRepository jornalistaRepository;

    @Override
    public void cadastrar(Jornalista jornalista) {
        jornalistaRepository.save(jornalista);
    }

    @Override
    public List<Jornalista> listarTodos() {
        return jornalistaRepository.findAll();
    }

    @Override
    public void remover(String idJornalista) {
        jornalistaRepository.deleteById(idJornalista);
    }

}
