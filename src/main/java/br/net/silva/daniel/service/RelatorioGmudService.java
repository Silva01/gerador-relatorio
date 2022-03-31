package br.net.silva.daniel.service;

import br.net.silva.daniel.dto.GmudDTO;
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
    private ExcelService excelService;

    @Autowired
    public RelatorioGmudService(RequestHttpService httpService, ExcelService excelService) {
        this.httpService = httpService;
        this.excelService = excelService;
    }

    public void gerar() throws Exception {
        final List<GmudDTO> response = httpService.request(query);
        excelService.criarNovo(excelService.convertParaDTOExcel(response), "relatorio.xls");
    }
}
