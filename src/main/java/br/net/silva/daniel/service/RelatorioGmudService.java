package br.net.silva.daniel.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RelatorioGmudService {

    @Value("${interpretador.itsm.query}")
    private String query;

    private RequestHttpService httpService;

    @Autowired
    public RelatorioGmudService(RequestHttpService httpService) {
        this.httpService = httpService;
    }

    public void gerar() throws JSONException, JsonProcessingException {
        final List<Map<String, Object>> response = httpService.request(query);

    }
}
