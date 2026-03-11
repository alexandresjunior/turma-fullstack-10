package br.com.treina.recife.sgp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.treina.recife.sgp.api.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByUsuario_Id(Long id);

    List<Tarefa> findByProjeto_Id(Long id);

    List<Tarefa> findByProjeto_IdAndUsuario_Id(Long idProjeto, Long idUsuario);
    
}
