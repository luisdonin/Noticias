package com.br.td.utfpr.edu.tsi.news.servico;

import com.br.td.utfpr.edu.tsi.news.modelo.Reportagem;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import jakarta.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Service
public class Indexador {

    private final Http2SolrClient solr;

    public Indexador() {
        String urlString = "http://localhost:8983/solr/reportagens";
        this.solr = new Http2SolrClient.Builder(urlString).build();
    }

    public void indexar(Reportagem reportagem) {
        if (reportagem.getId() == null || reportagem.getReportConteudo() == null || reportagem.getReportConteudo().isEmpty()) {
            System.err.println("Skipping invalid document: " + reportagem);
            return;
        }
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", reportagem.getId());
        document.addField("reportConteudo", reportagem.getReportConteudo());

        try {
            solr.add(document);
            solr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void indexar(List<Reportagem> reportagens) {
        List<SolrInputDocument> documents = new ArrayList<>();
        for (Reportagem r : reportagens) {
            if (r.getId() == null || r.getReportConteudo() == null || r.getReportConteudo().isEmpty()) {
                System.err.println("Skipping invalid document: " + r);
                continue;
            }
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", r.getId());
            document.addField("reportConteudo", r.getReportConteudo());
            documents.add(document);
        }
        try {
            if (!documents.isEmpty()) {
                solr.add(documents);
                solr.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> procurar(String campo, String termo) {
        List<String> ids = new ArrayList<>();
        try {
            SolrQuery query = new SolrQuery();
            query.setQuery(campo + ":" + termo);
            QueryResponse response = solr.query(query);
            SolrDocumentList documents = response.getResults();

            for (SolrDocument doc : documents) {
                ids.add((String) doc.getFieldValue("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }

    @PreDestroy
    public void close() {
        try {
            solr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}