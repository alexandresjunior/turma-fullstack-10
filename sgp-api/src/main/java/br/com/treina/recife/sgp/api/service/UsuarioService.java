package br.com.treina.recife.sgp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treina.recife.sgp.api.model.Usuario;
import br.com.treina.recife.sgp.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // SELECT * FROM TB_USUARIOS
    public List<Usuario> listarUsuarios() {
        // TODO: Ocultar senha.
        return usuarioRepository.findAll();
    }

    // SELECT * FROM TB_USUARIOS WHERE ID = ?
    public Optional<Usuario> obterDadosDoUsuario(Long id) {
        return usuarioRepository.findById(id);
    }

    // INSERT INTO TB_USUARIOS ...
    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // UPDATE TB_USUARIOS ... WHERE ID = ?
    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    // DELETE FROM TB_USUARIOS WHERE ID = ?
    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    
}
