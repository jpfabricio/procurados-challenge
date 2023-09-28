package br.com.fiap.procurados.DTO.fbi;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.List;

@Data
public class PaginacaoFbiDTO {

    @Expose
    private int total;

    @Expose
    private List<ProcuradoFbiDTO> items;

    @Expose
    private int page;
}
