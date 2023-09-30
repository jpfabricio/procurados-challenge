package br.com.fiap.procurados.service;

import br.com.fiap.procurados.DTO.fbi.ProcuradoFbiDTO;
import br.com.fiap.procurados.DTO.interpol.PaginacaoInterpolDTO;
import br.com.fiap.procurados.DTO.interpol.ProcuradoInterpolDTO;
import br.com.fiap.procurados.model.Imagem;
import br.com.fiap.procurados.model.Procurado;
import br.com.fiap.procurados.repository.ProcuradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcuradoService {

    @Autowired
    private ProcuradoRepository repository;

    @Autowired
    private RestService restService;

    public Procurado buscaPorIdFbiOuIdInterpol(String idFbiOuIdInterpol){
        Procurado procurado = repository.findByIdFbiOuIdInterpol(idFbiOuIdInterpol);
        return procurado;
    }

    public List<Procurado> listaProcurados(String nome){
        List<Procurado> procurado = repository.listaComFiltroOpcionalNome(nome);
        return procurado;
    }

    public void salvaProcuradosFbi(){
        List<ProcuradoFbiDTO> procurados = restService.buscaTodosProcuradosFbi();

        procurados.parallelStream().filter(procurado -> buscaPorIdFbiOuIdInterpol(procurado.getIdFbi()) == null).forEach(procurado -> {
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

        procurados.getListaDeProcuradosInterpol().getListaDeProcurados().parallelStream().filter(procurado -> buscaPorIdFbiOuIdInterpol(procurado.getIdInterpol()) == null).forEach(procurado -> {
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