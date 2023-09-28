package br.com.fiap.procurados.DTO.interpol;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class ProcuradoInterpolDTO {

    @Expose
    @SerializedName("date_of_birth")
    private String dataNascimento;

    @Expose
    @SerializedName("nationalities")
    private List<String> nacionalidades;

    @Expose
    @SerializedName("entity_id")
    private String idInterpol;

    @Expose
    @SerializedName("name")
    private String nome;

    @Expose
    @SerializedName("forename")
    private String sobrenome;

    @Expose
    @SerializedName("_links")
    private LinksInterpolDTO linksImagem;
}
