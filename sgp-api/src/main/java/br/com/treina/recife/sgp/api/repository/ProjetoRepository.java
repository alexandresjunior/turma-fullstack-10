package br.com.treina.recife.sgp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.treina.recife.sgp.api.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    
}
