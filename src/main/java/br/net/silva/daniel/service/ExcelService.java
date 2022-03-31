package br.net.silva.daniel.service;

import br.net.silva.daniel.dto.ExcelDTO;
import br.net.silva.daniel.dto.GmudDTO;
import br.net.silva.daniel.dto.anotacoes.Planilha;
import br.net.silva.daniel.dto.anotacoes.Salvar;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExcelService {


    public String criarNovo(final List<List<Object>> dados, final String destino) throws Exception {

        //InputStream input = new FileInputStream(new File(destino));
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = null;
        boolean loadPlanilha = true;
        boolean isTitle = true;
        int linha = 1;

        for (List<?> lista : dados) {

            int coluna = 0;
            linha = 1;
            loadPlanilha = true;

            for (Object objeto : lista) {
                Class<?> classe = objeto.getClass();


                if (loadPlanilha) {

                    for (Constructor<?> construtor: classe.getConstructors()) {
                        if (construtor.isAnnotationPresent(Planilha.class)) {

                            if (construtor.getAnnotation(Planilha.class).load() > 0) {
                                sheet = workbook.getSheet(construtor.getAnnotation(Planilha.class).nome());
                            } else {
                                sheet = workbook.createSheet(construtor.getAnnotation(Planilha.class).nome());
                            }

                            loadPlanilha = false;
                        }
                    }

                }

                if (isTitle) {
                    Row row = sheet.createRow(0);
                    for (Method metodo  : classe.getMethods()) {
                        if (metodo.isAnnotationPresent(Salvar.class)) {
                            Cell cell = row.createCell(metodo.getAnnotation(Salvar.class).tituloPosicao());
                            cell.setCellValue(metodo.getAnnotation(Salvar.class).titulo());
                        }
                    }

                    isTitle = false;

                }

                coluna = 0;
                Row row = sheet.createRow(linha++);
                for (Method metodo  : classe.getMethods()) {
                    if (metodo.isAnnotationPresent(Salvar.class)) {

                        int pos = linha;
                        int posCol = coluna++;

                        if (metodo.getAnnotation(Salvar.class).coluna() > -1) {
                            posCol = metodo.getAnnotation(Salvar.class).coluna();
                        }


                        Cell cell = row.createCell(posCol);

                        Object valor = metodo.invoke(objeto);

                        if (valor instanceof Integer) {
                            cell.setCellValue((Integer) valor);
                        } else if (valor instanceof Float) {
                            cell.setCellValue((Float) valor);
                        } else if (valor instanceof String) {
                            cell.setCellValue(valor.toString());
                        }

                    }

                }


                coluna = 0;

            }

        }

        FileOutputStream output = new FileOutputStream(destino);
        workbook.write(output);
        output.close();


        return destino;
    }

    private void copyFile(File origem, File destino) throws Exception {

        InputStream streamOri = new FileInputStream(origem);

        OutputStream streamDest = new FileOutputStream(destino);

        byte[] buf = new byte[1024];
        int len;

        while ((len = streamOri.read(buf)) > 0) {
            streamDest.write(buf, 0, len);
        }

        streamOri.close();

        streamDest.close();

    }

    public List<List<Object>> convertParaDTOExcel(List<GmudDTO> pbis) {
        List<List<Object>> listaExcel = new ArrayList<>();
        listaExcel.add(pbis
                .stream()
                .map(d -> ExcelDTO
                        .builder()
                        .id(d.getId())
                        .dataFim(d.getDataFim())
                        .descricao(d.getDescricao())
                        .dataInicio(d.getDataInicio())
                        .impacto(d.getImpacto())
                        .prioridade(d.getPrioridade())
                        .status(d.getStatus())
                        .solicitanteGrupo(d.getSolicitanteGrupo())
                        .build())
                .collect(Collectors.toList()));
        return listaExcel;
    }
}
