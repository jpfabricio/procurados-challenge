package br.com.fiap.procurados.repository.rest;

import br.com.fiap.procurados.DTO.fbi.PaginacaoFbiDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FbiAPIRepository {

    @GET("/wanted")
    Call<PaginacaoFbiDTO> busca(@Query(value = "pageSize") String pageSize, @Query(value = "page") int page);

}
