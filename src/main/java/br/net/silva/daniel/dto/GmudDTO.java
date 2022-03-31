package br.net.silva.daniel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GmudDTO {

    @JsonProperty("ChangeID")
    public String id;

    @JsonProperty("Status")
    public String status;

    @JsonProperty("Prioridade")
    public String prioridade;

    @JsonProperty("SolicitanteGrupo")
    public String solicitanteGrupo;

    @JsonProperty("DataInicio")
    public String dataInicio;

    @JsonProperty("DataFim")
    public String dataFim;

    @JsonProperty("Impacto")
    public String impacto;

    @JsonProperty("Descricao")
    public String descricao;
}
