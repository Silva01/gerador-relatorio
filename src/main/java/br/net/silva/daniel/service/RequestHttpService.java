package br.net.silva.daniel.service;

import br.net.silva.daniel.dto.GmudDTO;
import br.net.silva.daniel.dto.RequestDTO;
import br.net.silva.daniel.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RequestHttpService {

    @Value("${interpretador.itsm.url}")
    private String url;

    public List<GmudDTO> request(String query) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<>(JsonUtil.convertObjectToJsonString(RequestDTO.builder().query(query).build()), headers);

        return Arrays.asList(restTemplate.postForObject(url, request, GmudDTO[].class));
    }
}
