package br.net.silva.daniel.dto;

import br.net.silva.daniel.dto.anotacoes.Planilha;
import br.net.silva.daniel.dto.anotacoes.Salvar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ExcelDTO {
    public String id;
    public String status;
    public String prioridade;
    public String solicitanteGrupo;
    public String dataInicio;
    public String dataFim;
    public String impacto;
    public String descricao;

    @Planilha(load = 0, nome = "GMUD")
    public ExcelDTO() {}

    @Salvar(coluna = 0, linha = -1, titulo = "CRQ", tituloPosicao = 0)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Salvar(coluna = 1, linha = -1, titulo = "Status", tituloPosicao = 1)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Salvar(coluna = 2, linha = -1, titulo = "Prioridade", tituloPosicao = 2)
    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    @Salvar(coluna = 3, linha = -1, titulo = "Solicitante Grupo", tituloPosicao = 3)
    public String getSolicitanteGrupo() {
        return solicitanteGrupo;
    }

    public void setSolicitanteGrupo(String solicitanteGrupo) {
        this.solicitanteGrupo = solicitanteGrupo;
    }

    @Salvar(coluna = 4, linha = -1, titulo = "Data Inicio", tituloPosicao = 4)
    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Salvar(coluna = 5, linha = -1, titulo = "Data Fim", tituloPosicao = 5)
    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    @Salvar(coluna = 6, linha = -1, titulo = "Impacto", tituloPosicao = 6)
    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(String impacto) {
        this.impacto = impacto;
    }

    @Salvar(coluna = 7, linha = -1, titulo = "Descricao", tituloPosicao = 7)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
