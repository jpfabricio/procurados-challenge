package br.com.fiap.procurados.DTO.interpol;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class LinksInterpolDTO {

    @Expose
    @SerializedName("thumbnail")
    private ImagemInterpolDTO imagem;
}
