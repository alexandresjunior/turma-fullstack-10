package br.com.treina.recife.bank.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.treina.recife.bank.api.model.ContaPoupanca;

@Repository
public interface ContaPoupancaRepository extends JpaRepository<ContaPoupanca, Long> {

    List<ContaPoupanca> findByConta_Titular_Id(Long idTitular);

    List<ContaPoupanca> findByConta_SaldoGreaterThanEqual(Double saldo);

    List<ContaPoupanca> findByTaxaRendimentoLessThanEqual(Double taxaRendimento);
    
}
