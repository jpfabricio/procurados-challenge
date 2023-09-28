package br.com.fiap.procurados.DTO.interpol;

import br.com.fiap.procurados.DTO.fbi.ProcuradoFbiDTO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class PaginacaoInterpolDTO {

    @Expose
    private int total;

    @Expose
    @SerializedName("_embedded")
    private ListaDeProcuradosInterpolDTO listaDeProcuradosInterpol;

    @Expose
    @SerializedName("query")
    private DadosPaginacaoDTO dadosPaginacao;
}
