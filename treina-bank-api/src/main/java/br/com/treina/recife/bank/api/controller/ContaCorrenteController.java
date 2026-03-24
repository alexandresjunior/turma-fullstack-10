package br.com.treina.recife.bank.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treina.recife.bank.api.service.ContaCorrenteService;

@RestController
@RequestMapping("/contas-correntes")
public class ContaCorrenteController {
    
    @Autowired
    private ContaCorrenteService contaCorrenteService;

}
