package br.com.fiap.procurados.service;

import br.com.fiap.procurados.DTO.fbi.PaginacaoFbiDTO;
import br.com.fiap.procurados.DTO.fbi.ProcuradoFbiDTO;
import br.com.fiap.procurados.DTO.interpol.PaginacaoInterpolDTO;
import br.com.fiap.procurados.repository.rest.FbiAPIRepository;
import br.com.fiap.procurados.repository.rest.InterpolAPIRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static br.com.fiap.procurados.config.RetrofitConfig.createRepository;

@Service
public class RestService {

    @Value("${fbi.url}")
    private String fbiBaseUrl;

    @Value("${interpol.url}")
    private String interpolBaseUrl;

    public PaginacaoFbiDTO buscaProcuradosFbi(String itensPorPagina, int pagina){
        Response<PaginacaoFbiDTO> response = null;
        try {
            Call<PaginacaoFbiDTO> call = createRepository(fbiBaseUrl, FbiAPIRepository.class).busca(itensPorPagina, pagina);
            response = call.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.body();
    }

    public List<ProcuradoFbiDTO> buscaTodosProcuradosFbi(){
        List<ProcuradoFbiDTO> procurados = new ArrayList<ProcuradoFbiDTO>();
        boolean isListaVazia = false;
        int pagina = 1;

        while (isListaVazia == false){
            PaginacaoFbiDTO busca = buscaProcuradosFbi("50", pagina);
            procurados.addAll(busca.getItems());
            isListaVazia = busca.getItems().isEmpty();
            pagina++;
        }
        return procurados;
    }

    public PaginacaoInterpolDTO buscaProcuradosInterpol(String itensPorPagina, int pagina){
        Response<PaginacaoInterpolDTO> response = null;
        try {
            Call<PaginacaoInterpolDTO> call = createRepository(interpolBaseUrl, InterpolAPIRepository.class).busca(itensPorPagina, pagina);
            response = call.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.body();
    }
}
