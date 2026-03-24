package br.com.treina.recife.bank.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treina.recife.bank.api.dto.ContaProjecao;
import br.com.treina.recife.bank.api.dto.DadosTitular;
import br.com.treina.recife.bank.api.model.Titular;
import br.com.treina.recife.bank.api.repository.TitularRepository;

@Service
public class TitularService {
    
    @Autowired
    private TitularRepository titularRepository;

    public Titular cadastrar(DadosTitular dados) {
        Titular titular = new Titular();

        titular.setNome(dados.nome());
        titular.setEmail(dados.email());
        titular.setTelefone(dados.telefone());

        return titularRepository.save(titular);
    }

    public Titular buscarPeloEmail(String email) {
       return titularRepository.findByEmail(email)
                            .orElseThrow(() -> new RuntimeException("Titular não encontrado."));
    }

    public List<ContaProjecao> listarContasDoTitular(Long idTitular) {
        return titularRepository.listarContasDoTitular(idTitular);
    }

}
