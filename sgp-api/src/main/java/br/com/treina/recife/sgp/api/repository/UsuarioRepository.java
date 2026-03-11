package br.com.treina.recife.sgp.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.treina.recife.sgp.api.enums.StatusUsuario;
import br.com.treina.recife.sgp.api.model.Usuario;
import java.time.LocalDate;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCpf(String cpf);

    Optional<Usuario> findByEmailAndSenha(String email, String senha);

    List<Usuario> findByStatus(StatusUsuario status);

    List<Usuario> findByDataNascimentoBetween(LocalDate dataInicio, LocalDate dataFim);

    // *andre -> Alexandre, Andreza, Andrea, Andressa, Andrelina, Andrezza
    List<Usuario> findByNomeContainingIgnoreCase(String seqCaracteres);

}
