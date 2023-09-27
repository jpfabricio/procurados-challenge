package br.com.fiap.procurados.service;

import br.com.fiap.procurados.DTO.PaginacaoFbiDTO;
import br.com.fiap.procurados.DTO.ProcuradoDTO;
import br.com.fiap.procurados.model.Procurado;
import br.com.fiap.procurados.repository.ProcuradoRepository;
import br.com.fiap.procurados.repository.rest.FbiAPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static br.com.fiap.procurados.config.RetrofitConfig.createRepository;

@Service
public class ProcuradoService {

    @Autowired
    private ProcuradoRepository repository;

    @Value("${fbi.url}")
    private String fbiBaseUrl;

    public void salva(Procurado procurado){
        repository.save(procurado);
    }

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

    public List<ProcuradoDTO> buscaAteAcabar(){
        List<ProcuradoDTO> procurados = new ArrayList<ProcuradoDTO>();
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
}