package br.com.fiap.procurados.controller;

import br.com.fiap.procurados.DTO.PaginacaoFbiDTO;
import br.com.fiap.procurados.DTO.ProcuradoDTO;
import br.com.fiap.procurados.service.ProcuradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("procurado")
public class ProcuradoController {

    @Autowired
    private ProcuradoService service;

    @GetMapping
    @ResponseBody
    public PaginacaoFbiDTO buscaProcuradosFbi(){
        PaginacaoFbiDTO busca = service.buscaProcuradosFbi("20", 1);
        return busca;
    }

    @GetMapping("/lista")
    @ResponseBody
    public List<ProcuradoDTO> buscaAteAcabar(){
        List<ProcuradoDTO> procurados = service.buscaAteAcabar();
        System.out.println(procurados.size());
        return procurados;
    }

}