package br.com.fiap.procurados.service;

import br.com.fiap.procurados.DTO.fbi.ImagemFbiDTO;
import br.com.fiap.procurados.DTO.fbi.PaginacaoFbiDTO;
import br.com.fiap.procurados.DTO.fbi.ProcuradoFbiDTO;
import br.com.fiap.procurados.DTO.interpol.PaginacaoInterpolDTO;
import br.com.fiap.procurados.DTO.interpol.ProcuradoInterpolDTO;
import br.com.fiap.procurados.model.Caracteristica;
import br.com.fiap.procurados.model.Classificacao;
import br.com.fiap.procurados.model.Imagem;
import br.com.fiap.procurados.model.Procurado;
import br.com.fiap.procurados.repository.ProcuradoRepository;
import br.com.fiap.procurados.repository.rest.FbiAPIRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.fiap.procurados.config.RetrofitConfig.createRepository;

@Service
public class ProcuradoService {

    @Autowired
    private ProcuradoRepository repository;

    @Autowired
    private RestService restService;

    public Procurado buscaPorIdFbi(String idFbi){
        Procurado procurado = repository.findByIdFbi(idFbi);
        return procurado;
    }

    public Procurado buscaPorIdInterpol(String idInterpol){
        Procurado procurado = repository.findByIdInterpol(idInterpol);
        return procurado;
    }

    public void salvaProcuradosFbi(){
        List<ProcuradoFbiDTO> procurados = restService.buscaTodosProcuradosFbi();

        procurados.parallelStream().filter(procurado -> buscaPorIdFbi(procurado.getIdFbi()) == null).forEach(procurado -> {
            Procurado procuradoModel = criaProcuradoAPartirDeProcuradoFbiDto(procurado);
            repository.save(procuradoModel);
        });


    }

    private Procurado criaProcuradoAPartirDeProcuradoFbiDto(ProcuradoFbiDTO procurado){
        Procurado procuradoModel = new Procurado(procurado);

        List<Imagem> imagemModels = procurado.getImagens().stream()
                .map(imagemFbiDTO -> new Imagem(imagemFbiDTO.getDescricao(), imagemFbiDTO.getUrlImagem(), procuradoModel))
                .collect(Collectors.toList());

        procuradoModel.setImagens(imagemModels);

        return procuradoModel;
    }

    public void salvaProcuradosInterpol(){
        PaginacaoInterpolDTO procurados = restService.buscaProcuradosInterpol("1000", 1);

        procurados.getListaDeProcuradosInterpol().getListaDeProcurados().parallelStream().filter(procurado -> buscaPorIdInterpol(procurado.getIdInterpol()) == null).forEach(procurado -> {
            Procurado procuradoModel = criaProcuradoAPartirDeProcuradoInterpolDTO(procurado);
            repository.save(procuradoModel);
        });
    }

    private Procurado criaProcuradoAPartirDeProcuradoInterpolDTO(ProcuradoInterpolDTO procurado){
        Procurado procuradoModel = new Procurado(procurado);
        List<Imagem> imagemModels = List.of(new Imagem(null, procurado.getLinksImagem().getImagem() != null ? procurado.getLinksImagem().getImagem().getLinkImagem() : null, procuradoModel));

        procuradoModel.setImagens(imagemModels);

        return procuradoModel;
    }
}