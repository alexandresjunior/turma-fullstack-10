package br.com.treina.recife.bank.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.treina.recife.bank.api.dto.ContaProjecao;
import br.com.treina.recife.bank.api.dto.DadosTitular;
import br.com.treina.recife.bank.api.model.Titular;
import br.com.treina.recife.bank.api.service.TitularService;

@RestController
@RequestMapping("/titulares")
public class TitularController {
    
    @Autowired
    private TitularService titularService;

    @PostMapping
    public ResponseEntity<Titular> cadastrar(@RequestBody DadosTitular dados) {
        return ResponseEntity.status(HttpStatus.CREATED).body(titularService.cadastrar(dados));
    }

    @GetMapping("/contas/{idTitular}")
    public ResponseEntity<List<ContaProjecao>> listarContasDoTitular(@PathVariable Long idTitular) {
        // return ResponseEntity.status(HttpStatus.OK).body(titularService.listarContasDoTitular(idTitular));
        return ResponseEntity.ok(titularService.listarContasDoTitular(idTitular));
    }

    @GetMapping("/busca")       // GET /titulares/busca?email=alex@dev.com
    public ResponseEntity<Titular> buscarPeloEmail(@RequestParam String email) {
        return ResponseEntity.ok(titularService.buscarPeloEmail(email));
    }

}
