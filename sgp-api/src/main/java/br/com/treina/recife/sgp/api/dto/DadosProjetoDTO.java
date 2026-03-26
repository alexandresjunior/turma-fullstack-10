package br.com.treina.recife.sgp.api.dto;

import java.time.LocalDate;

import br.com.treina.recife.sgp.api.enums.StatusProjeto;
import br.com.treina.recife.sgp.api.model.Projeto;
import br.com.treina.recife.sgp.api.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosProjetoDTO(
    @NotBlank
    String nome,
    String descricao,
    @NotNull
    LocalDate dataInicio,
    LocalDate dataFinal,
    @NotNull
    StatusProjeto status,
    @NotNull
    Long responsavelId
) {

    public Projeto toModel() {
        Projeto projeto = new Projeto();

        projeto.setNome(nome);
        projeto.setDescricao(descricao);
        projeto.setDataInicio(dataInicio);
        projeto.setDataFinal(dataFinal);
        projeto.setStatus(status);

        Usuario usuario = new Usuario();
        usuario.setId(responsavelId);
        projeto.setResponsavel(usuario);

        return projeto;
    }

}
