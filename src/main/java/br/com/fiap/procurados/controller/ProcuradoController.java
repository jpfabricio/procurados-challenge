package br.com.fiap.procurados.controller;

import br.com.fiap.procurados.DTO.fbi.PaginacaoFbiDTO;
import br.com.fiap.procurados.DTO.fbi.ProcuradoFbiDTO;
import br.com.fiap.procurados.DTO.interpol.PaginacaoInterpolDTO;
import br.com.fiap.procurados.service.ProcuradoService;
import br.com.fiap.procurados.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("procurado")
public class ProcuradoController {

    @Autowired
    private ProcuradoService service;

    @Autowired
    private RestService restService;

    @PostMapping("/fbi/popula")
    @ResponseBody
    public void populaBancoComProcuradosFbi(){
        service.salvaProcuradosFbi();
    }

    @GetMapping("/fbi")
    @ResponseBody
    public PaginacaoFbiDTO buscaProcuradosFbi(){
        PaginacaoFbiDTO busca = restService.buscaProcuradosFbi("20", 1);
        return busca;
    }

    @GetMapping("/fbi/lista")
    @ResponseBody
    public List<ProcuradoFbiDTO> buscaAteAcabar(){
        List<ProcuradoFbiDTO> procurados = restService.buscaTodosProcuradosFbi();
        System.out.println(procurados.size());
        return procurados;
    }

    @GetMapping("/interpol")
    @ResponseBody
    public PaginacaoInterpolDTO buscaProcuradosInterpol(){
        PaginacaoInterpolDTO busca = restService.buscaProcuradosInterpol("160", 1);
        return busca;
    }

}