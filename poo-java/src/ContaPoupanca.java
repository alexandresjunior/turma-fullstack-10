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

    @Override
    public void cadastrar() {
        System.out.println("Conta poupança cadastrada com sucesso!");
    }

    @Override
    public void atualizar() {
        System.out.println("Conta poupança atualizada com sucesso!");
    }

    @Override
    public void deletar() {
        System.out.println("Conta poupança deletada com sucesso!");
    }

    @Override
    public void listar() {
        System.out.println("Contas poupanças listadas com sucesso!");
    }

}
