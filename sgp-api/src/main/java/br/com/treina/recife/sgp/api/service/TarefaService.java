package br.com.treina.recife.sgp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treina.recife.sgp.api.model.Tarefa;
import br.com.treina.recife.sgp.api.repository.TarefaRepository;

@Service
public class TarefaService {
    
    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> obterDadosDaTarefa(Long id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa cadastrarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizarTarefa(Long id, Tarefa tarefa) {
        tarefa.setId(id);
        return tarefaRepository.save(tarefa);
    }

    public void excluirTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

}
