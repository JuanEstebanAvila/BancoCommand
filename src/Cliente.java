public class Cliente {
    public static void main(String[] args) {
        CuentaBancaria ana = new CuentaBancaria("Ana", 1000);
        CuentaBancaria luis = new CuentaBancaria("Luis", 500);
        CajeroAutomatico cajero = new CajeroAutomatico();

        System.out.println("Ana, su saldo es inicial es :" + "1000");
        System.out.println("Luis, su saldo inicial es : 500");

        System.out.println("Transacciones distintas");
        cajero.ejecutarComando(new DepositarCommand(ana, 250));
        cajero.ejecutarComando(new RetirarCommand(ana, 100));
        cajero.ejecutarComando(new TransferirCommand(ana, luis, 300));
        cajero.ejecutarComando(new ConsultarSaldoCommand(luis));

        System.out.println("\nOperación rechazada (fondos insuficientes)");
        cajero.ejecutarComando(new RetirarCommand(ana, 5000));

        System.out.println("\nDeshacer operaciones");
        cajero.deshacerUltimo();
        cajero.deshacerUltimo(); 
        cajero.deshacerUltimo(); 
        cajero.deshacerUltimo(); 

        System.out.println("\nSaldos finales");
        cajero.ejecutarComando(new ConsultarSaldoCommand(ana));
        cajero.ejecutarComando(new ConsultarSaldoCommand(luis));
    }
}
