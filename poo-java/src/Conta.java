public abstract class Conta implements Repositorio {

    private Long idBanco;
    private Long numAgencia;
    private String numConta;
    private Integer digito;
    private Double saldo;
    private Titular titular;

    public Conta(Long idBanco, Long numAgencia, String numConta, Integer digito, Titular titular) {
        this.idBanco = idBanco;
        this.numAgencia = numAgencia;
        this.numConta = numConta;
        this.digito = digito;
        this.saldo = 0.0;
        this.titular = titular;
    }

    public Long getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Long idBanco) {
        this.idBanco = idBanco;
    }

    public Long getNumAgencia() {
        return numAgencia;
    }

    public void setNumAgencia(Long numAgencia) {
        this.numAgencia = numAgencia;
    }

    public String getNumConta() {
        return numConta;
    }

    public void setNumConta(String numConta) {
        this.numConta = numConta;
    }

    public Integer getDigito() {
        return digito;
    }

    public void setDigito(Integer digito) {
        this.digito = digito;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public void depositar(Double valor) {
        saldo += valor;
    }

    public boolean sacar(Double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        } 
            
        System.out.println("Saldo indisponível para saque!");
        return false;
    }

    public void fazerPix(Double valor, Conta contaDestino) {
        if (this.sacar(valor)) {
            contaDestino.depositar(valor);
        } else {
            System.out.println("Saldo indisponível para PIX!");
        }
    }

}
