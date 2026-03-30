package br.com.treina.recife.sgp.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treina.recife.sgp.api.dto.DadosUsuarioDTO;
import br.com.treina.recife.sgp.api.dto.UsuarioDTO;
import br.com.treina.recife.sgp.api.exception.RecursoNaoEncontradoException;
import br.com.treina.recife.sgp.api.exception.RegraDeNegocioException;
import br.com.treina.recife.sgp.api.model.Usuario;
import br.com.treina.recife.sgp.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // SELECT * FROM TB_USUARIOS
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        List<UsuarioDTO> dtos = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            dtos.add(usuario.toDTO());
        }

        return dtos;
    }

    // SELECT * FROM TB_USUARIOS WHERE ID = ?
    public UsuarioDTO obterDadosDoUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com o ID: " + id));

        return usuario.toDTO();
    }

    // INSERT INTO TB_USUARIOS ...
    public Usuario cadastrarUsuario(DadosUsuarioDTO usuario) {
        String cpf = usuario.cpf();

        Optional<Usuario> usuarioOpt = usuarioRepository.findByCpf(cpf);

        if (usuarioOpt.isPresent()) {
            throw new RegraDeNegocioException("Já existe um usuário cadastrado com o CPF: " + cpf);
        }

        return usuarioRepository.save(usuario.toModel());
    }

    // UPDATE TB_USUARIOS ... WHERE ID = ?
    public Usuario atualizarUsuario(Long id, DadosUsuarioDTO dados) {
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Não é possível atualizar. Usuário não encontrado com ID: " + id);
        }

        Usuario usuario = dados.toModel();
        usuario.setId(id); // Garante que o usuário vai ser atualizado e não criado um novo!
        return usuarioRepository.save(usuario);
    }

    // DELETE FROM TB_USUARIOS WHERE ID = ?
    public void excluirUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Não é possível excluir. Usuário não encontrado com ID: " + id);
        }

        usuarioRepository.deleteById(id);
    }

    // SELECT * FROM TB_USUARIOS WHERE CPF = ?
    public UsuarioDTO buscarUsuarioPeloCPF(String cpf) {
        Usuario usuario = usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com o CPF: " + cpf));

        return usuario.toDTO();
    }

    // SELECT * FROM TB_USUARIOS WHERE EMAIL = ? AND SENHA = ?
    public UsuarioDTO buscarUsuarioPeloEmailSenha(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha)
                .orElseThrow(() -> new RegraDeNegocioException("Email ou senha inválidos."));

        return usuario.toDTO();
    }

}
