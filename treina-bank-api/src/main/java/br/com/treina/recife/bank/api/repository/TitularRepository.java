package br.com.treina.recife.bank.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.treina.recife.bank.api.dto.ContaProjecao;
import br.com.treina.recife.bank.api.model.Titular;

@Repository
public interface TitularRepository extends JpaRepository<Titular, Long>{

    Optional<Titular> findByEmail(String email);

    @Query(value = """
        SELECT id, idBanco, numAgencia, numConta, digito FROM TB_CONTAS_CORRENTE WHERE TITULAR_ID = :idTitular 
        UNION ALL 
        SELECT id, idBanco, numAgencia, numConta, digito FROM TB_CONTAS_POUPANCA WHERE TITULAR_ID = :idTitular
    """, nativeQuery = true)
    List<ContaProjecao> listarContasDoTitular(@Param("idTitular") Long idTitular);
   
}
