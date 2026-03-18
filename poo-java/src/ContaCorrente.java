public class ContaCorrente extends Conta {

    private Double limiteChequeEspecial;

    public ContaCorrente(Long idBanco, Long numAgencia, String numConta, Integer digito, Titular titular, Double limite) {
        super(idBanco, numAgencia, numConta, digito, titular);
        this.limiteChequeEspecial = limite;
    }

    public Double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(Double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public boolean sacar(Double valor) {
        Double saldo = this.getSaldo();

        if (valor <= saldo + limiteChequeEspecial) {
            this.setSaldo(saldo - valor);
            return true;
        } else {
            System.out.println("Saldo indisponível para saque, incluindo o limite com cheque especial!");
            return false;
        }
    }

}
