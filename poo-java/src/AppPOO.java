public class AppPOO {

    public static void main(String[] args) {
        Titular titular1 = new Titular("Alexandre de Souza Jr.", "souzajr.alexandre@gmail.com");

        ContaCorrente conta1 = new ContaCorrente(123L, 4567L, "345Z", 5, titular1, 1000.00);
        
        conta1.depositar(2500.00);
        
        System.out.println("Saldo inicial: " + conta1.getSaldo());
        
        conta1.sacar(3500.00);
        
        System.out.println("Saldo pós-saque: " + conta1.getSaldo());
        
        Titular titular2 = new Titular("Maria Alice", "maria.alice@gmail.com");

        ContaPoupanca conta2 = new ContaPoupanca(321L, 9867L, "678Y", 7, titular2, 0.10);

        conta1.fazerPix(3000.00, conta2);

        System.out.println("Saldo da Conta #1: " + conta1.getSaldo());
        System.out.println("Saldo da Conta #2: " + conta2.getSaldo());
        
        conta2.depositar(5000.00);
        
        System.out.println("Saldo da Conta #2 antes do saque: " + conta2.getSaldo());
        
        conta2.sacar(5500.00);
        
        System.out.println("Saldo da Conta #2 depois do saque: " + conta2.getSaldo());

        // Comportamentos específicos de cada classe
        conta1.cadastrar();
        conta2.cadastrar();
        titular1.cadastrar();

    }
    
}
