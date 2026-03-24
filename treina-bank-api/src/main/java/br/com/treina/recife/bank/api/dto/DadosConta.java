package br.com.treina.recife.bank.api.dto;

public record DadosConta(
    Long id,
    Long idBanco,
    Long numAgencia,
    String numConta,
    Integer digito,
    Double saldo,
    DadosTitular titular,
    Double limiteChequeEspecial,
    Double taxaRendimento
) {
    
}
