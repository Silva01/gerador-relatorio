package br.net.silva.daniel.job;

import br.net.silva.daniel.service.RelatorioGmudService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GerarRelatorioJob {

    private RelatorioGmudService service;

    @Autowired
    public GerarRelatorioJob(RelatorioGmudService service) {
        this.service = service;
    }

    @Scheduled(cron = "${relatorio.cron}")
    public void start() {
        try {
            service.gerar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
