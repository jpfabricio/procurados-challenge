package br.com.fiap.procurados.DTO;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.List;

@Data
public class PaginacaoFbiDTO {

    @Expose
    private int total;

    @Expose
    private List<ProcuradoDTO> items;

    @Expose
    private int page;
}
