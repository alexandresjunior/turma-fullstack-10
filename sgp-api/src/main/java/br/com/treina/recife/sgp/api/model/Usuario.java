package br.com.treina.recife.sgp.api.model;

import java.time.LocalDate;

import br.com.treina.recife.sgp.api.enums.StatusUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_USUARIOS")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // BIGINT PRIMARY KEY AUTO_INCREMENT

    // VARCHAR(50) NOT NULL
    @Column(nullable = false, length = 50)
    private String nome;

    // VARCHAR(11) NOT NULL UNIQUE
    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    // VARCHAR(255) NOT NULL UNIQUE
    @Column(nullable = false, unique = true)
    private String email;

    // VARCHAR(19) NOT NULL
    @Column(nullable = false, length = 19)
    private String senha;

    // DATETIME NOT NULL
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusUsuario status;

}
