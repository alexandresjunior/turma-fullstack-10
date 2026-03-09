package br.com.treina.recife.sgp.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treina.recife.sgp.api.model.Tarefa;
import br.com.treina.recife.sgp.api.service.TarefaService;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> cadastrar(@RequestBody Tarefa tarefa) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(tarefaService.cadastrarTarefa(tarefa));
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listar() {
        return ResponseEntity.ok(tarefaService.listarTarefas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> obterDadosPeloId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaService.obterDadosDaTarefa(id);

        if (tarefa.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tarefa.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaService.obterDadosDaTarefa(id);

        if (tarefa.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        tarefaService.excluirTarefa(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa dados) {
        Optional<Tarefa> tarefa = tarefaService.obterDadosDaTarefa(id);

        if (tarefa.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tarefaService.atualizarTarefa(id, dados));
    }
    
}
