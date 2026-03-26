package br.com.treina.recife.sgp.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treina.recife.sgp.api.dto.DadosUsuarioDTO;
import br.com.treina.recife.sgp.api.dto.UsuarioDTO;
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
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        
        if (usuario.isPresent()) {
            return usuario.get().toDTO();
        }

        return null;
    }

    // INSERT INTO TB_USUARIOS ...
    public Usuario cadastrarUsuario(DadosUsuarioDTO usuario) {
        return usuarioRepository.save(usuario.toModel());
    }

    // UPDATE TB_USUARIOS ... WHERE ID = ?
    public Usuario atualizarUsuario(Long id, DadosUsuarioDTO dados) {
        Usuario usuario = dados.toModel();
        usuario.setId(id);  // Garante que o usuário vai ser atualizado e não criado um novo!
        return usuarioRepository.save(usuario);
    }

    // DELETE FROM TB_USUARIOS WHERE ID = ?
    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    // SELECT * FROM TB_USUARIOS WHERE CPF = ?
    public UsuarioDTO buscarUsuarioPeloCPF(String cpf) {
        Optional<Usuario> usuario = usuarioRepository.findByCpf(cpf);

        if (usuario.isPresent()) {
            return usuario.get().toDTO();
        }

        return null;
    }

    public UsuarioDTO buscarUsuarioPeloEmailSenha(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(email, senha);

        if (usuario.isPresent()) {
            return usuario.get().toDTO();
        }

        return null;
    }

}
