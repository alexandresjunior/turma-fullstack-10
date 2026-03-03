package br.com.treina.recife.sgp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.treina.recife.sgp.api.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
}
