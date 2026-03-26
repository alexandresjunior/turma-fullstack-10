package br.com.treina.recife.sgp.api.dto;

import java.time.LocalDate;

import br.com.treina.recife.sgp.api.enums.StatusUsuario;
import br.com.treina.recife.sgp.api.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosUsuarioDTO(
    @NotBlank @Size(min = 3, max = 50)
    String nome,
    @NotBlank @Size(max = 11)
    String cpf,
    @NotBlank @Email
    String email,
    @NotBlank @Size(max = 19)
    String senha,
    @NotNull
    LocalDate dataNascimento,
    @NotNull
    StatusUsuario status
) {

    public Usuario toModel() {
        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setDataNascimento(dataNascimento);
        usuario.setStatus(status);

        return usuario;
    }
    
}
