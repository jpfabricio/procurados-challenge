package br.com.fiap.procurados.DTO.interpol;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class DadosPaginacaoDTO {

    @Expose
    private int page;

    @Expose
    @SerializedName("resultPerPage")
    private int itensPorPagina;
}
