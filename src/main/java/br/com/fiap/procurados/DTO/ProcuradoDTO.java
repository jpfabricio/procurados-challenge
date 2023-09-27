package br.com.fiap.procurados.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProcuradoDTO {

    @Expose
    @SerializedName("title")
    private String nome;

    @Expose
    @SerializedName("sex")
    private String sexo;

    @Expose
    @SerializedName("race_raw")
    private String raca;

    @Expose
    @SerializedName("dates_of_birth_used")
    private List<String> dataAniversario;

    @Expose
    @SerializedName("nationality")
    private String nacionalidade;

    @Expose
    @SerializedName("languages")
    private List<String> lingua;

    @Expose
    @SerializedName("eyes")
    private String corDosOlhos;

    @Expose
    @SerializedName("height_max")
    private int altura;

    @Expose
    @SerializedName("weight")
    private String peso;

    @Expose
    @SerializedName("person_classification")
    private String classificacaoDaPessoa;

    @Expose
    @SerializedName("publication")
    private String dataPublicacao;

    @Expose
    @SerializedName("description")
    private String descricao;
}

