package br.com.fiap.procurados.DTO.fbi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ImagemFbiDTO {

    @Expose
    @SerializedName("original")
    private String urlImagem;

    @Expose
    @SerializedName("caption")
    private String descricao;
}
