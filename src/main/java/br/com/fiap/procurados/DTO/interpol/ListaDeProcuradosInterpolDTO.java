package br.com.fiap.procurados.DTO.interpol;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class ListaDeProcuradosInterpolDTO {

    @Expose
    @SerializedName("notices")
    private List<ProcuradoInterpolDTO> listaDeProcurados;
}
