public class App {
    
    public static void main(String[] args) {
        // Dados da conta bancaria do titular n. 1
        String nomeTitular = "Alexandre de Souza Jr.";
        String emailTitular = "souzajr.alexandre@gmail.com";
        String telefoneTitular = "(81) 9 9160 3025";
        Long idBanco = 123L;
        Long numAgencia = 5566L;
        String numConta = "789X";
        Integer digito = 4;
        Double saldo = 2500.00;

        // Acao de deposito
        Double deposito = 500.00;

        // Atualizando saldo
        saldo += deposito;

        // Acao de saque
        Double saque = 750.00;

        // Atualizando saldo
        if (saque <= saldo) {
            saldo -= saque;
        } else {
            System.out.println("Saldo indisponivel para saque.");
            return;
        }

        // Dados da conta bancaria do titular n. 2
        String nomeTitular2 = "Maria Alice";
        String emailTitular2 = "maria.alice@gmail.com";
        String telefoneTitular2 = "(81) 9 9876 5432";
        Long idBanco2 = 456L;
        Long numAgencia2 = 5796L;
        String numConta2 = "123Y";
        Integer digito2 = 7;
        Double saldo2 = 1000.00;
        
        // Acao de PIX
        Double valorPix = 150.00;

        if (saldo >= valorPix) {
            saldo -= valorPix;
            saldo2 += valorPix;
        } else {
            System.out.println("Saldo indisponivel para PIX.");
            return;
        }

    }

}
