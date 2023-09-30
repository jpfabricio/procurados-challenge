package br.com.fiap.procurados.controller;

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

    @GetMapping
    @ResponseBody
    public Procurado buscaProcuradosInterpolOuFbi(@RequestParam(name = "idFbiOuIdInterpol") String idFbiOuIdInterpol){
        return service.buscaPorIdFbiOuIdInterpol(idFbiOuIdInterpol);
    }

    @GetMapping("lista")
    @ResponseBody
    public List<Procurado> listaProcurados(@RequestParam(name = "nome", required = false) String nome){
        return service.listaProcurados(nome);
    }


}