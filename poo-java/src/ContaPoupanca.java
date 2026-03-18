public class ContaPoupanca extends Conta {

    private Double taxaRendimento;

    public ContaPoupanca(Long idBanco, Long numAgencia, String numConta, Integer digito, Titular titular, Double taxa) {
        super(idBanco, numAgencia, numConta, digito, titular);
        this.taxaRendimento = taxa;
    }

    public Double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(Double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    public void aplicarRendimento() {
        this.depositar(this.getSaldo() * taxaRendimento);
    }

}
