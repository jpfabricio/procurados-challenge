package br.com.fiap.procurados.repository.rest;

import br.com.fiap.procurados.DTO.fbi.PaginacaoFbiDTO;
import br.com.fiap.procurados.DTO.interpol.PaginacaoInterpolDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterpolAPIRepository {

    @GET("/notices/v1/red")
    Call<PaginacaoInterpolDTO> busca(@Query(value = "resultPerPage") String resultPerPage, @Query(value = "page") int page);

}
