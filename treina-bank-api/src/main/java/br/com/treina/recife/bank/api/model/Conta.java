package br.com.treina.recife.bank.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class Conta {

    @Column(nullable = false)
    private Long idBanco;

    @Column(nullable = false)
    private Long numAgencia;

    @Column(nullable = false, unique = true)
    private String numConta;

    @Column(nullable = false)
    private Integer digito;

    private Double saldo = 0.0;

    @ManyToOne
    @JoinColumn(nullable = false, name = "titular_id")
    private Titular titular;

}
