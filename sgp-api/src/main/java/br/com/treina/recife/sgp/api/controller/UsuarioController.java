package br.com.treina.recife.sgp.api.controller;

import java.util.List;
import java.util.Objects;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.treina.recife.sgp.api.dto.CredenciaisDTO;
import br.com.treina.recife.sgp.api.dto.UsuarioDTO;
import br.com.treina.recife.sgp.api.model.Usuario;
import br.com.treina.recife.sgp.api.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(usuarioService.cadastrarUsuario(usuario).toDTO());
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obterDadosPeloId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.obterDadosDoUsuario(id);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.obterDadosDoUsuario(id);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.excluirUsuario(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @RequestBody Usuario dados) {
        UsuarioDTO usuario = usuarioService.obterDadosDoUsuario(id);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuarioService.atualizarUsuario(id, dados).toDTO());
    }

    @GetMapping("/buscaPorCpf")
    // Exemplo de chamada à API: http://localhost:8080/api/usuarios/buscaPorCpf?cpf=12345678900
    public ResponseEntity<UsuarioDTO> consultarPeloCPF(@RequestParam String cpf) {
        UsuarioDTO usuario = usuarioService.buscarUsuarioPeloCPF(cpf);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/buscaPorCredenciais")
    public ResponseEntity<UsuarioDTO> consultarPelasCredenciais(@RequestBody CredenciaisDTO credenciais) {
        UsuarioDTO usuario = usuarioService.buscarUsuarioPeloEmailSenha(credenciais.email(), credenciais.senha());

        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

}
