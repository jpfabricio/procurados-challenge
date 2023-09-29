package br.com.fiap.procurados.controller;

import br.com.fiap.procurados.DTO.fbi.PaginacaoFbiDTO;
import br.com.fiap.procurados.DTO.fbi.ProcuradoFbiDTO;
import br.com.fiap.procurados.DTO.interpol.PaginacaoInterpolDTO;
import br.com.fiap.procurados.model.Procurado;
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

    @GetMapping("/fbi/{idFbi}")
    @ResponseBody
    public Procurado buscaProcuradoPorIdFbi(@PathVariable String idFbi){
        return service.buscaPorIdFbi(idFbi);
    }

    @PostMapping("/fbi/popula")
    @ResponseBody
    public void populaBancoComProcuradosFbi(){
        service.salvaProcuradosFbi();
    }

    @PostMapping("/interpol/popula")
    @ResponseBody
    public void populaBancoComProcuradosInterpol(){
        service.salvaProcuradosInterpol();
    }

    @GetMapping("/interpol")
    @ResponseBody
    public Procurado buscaProcuradosInterpol(@RequestParam(name = "idInterpol") String idInterpol){
        return service.buscaPorIdInterpol(idInterpol);
    }

}