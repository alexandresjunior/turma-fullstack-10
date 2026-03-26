package br.com.treina.recife.sgp.api.dto;

import java.time.LocalDate;

import br.com.treina.recife.sgp.api.enums.PrioridadeTarefa;
import br.com.treina.recife.sgp.api.enums.StatusTarefa;
import br.com.treina.recife.sgp.api.model.Projeto;
import br.com.treina.recife.sgp.api.model.Tarefa;
import br.com.treina.recife.sgp.api.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosTarefaDTO(
    @NotBlank
    String titulo,
    String descricao,
    @NotNull
    LocalDate dataCriacao,
    LocalDate dataConclusao,
    @NotNull
    PrioridadeTarefa prioridade,
    @NotNull
    StatusTarefa status,
    Long usuarioId,
    @NotNull
    Long projetoId
) {
    
    public Tarefa toModel() {
        Tarefa tarefa = new Tarefa();

        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);
        tarefa.setDataCriacao(dataCriacao);
        tarefa.setDataConclusao(dataConclusao);
        tarefa.setPrioridade(prioridade);
        tarefa.setStatus(status);

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        tarefa.setUsuario(usuario);

        Projeto projeto = new Projeto();
        projeto.setId(projetoId);
        tarefa.setProjeto(projeto);
        
        return tarefa;
    }

}
