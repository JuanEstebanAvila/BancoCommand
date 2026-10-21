public class ConsultarSaldoCommand implements Command {
    private final CuentaBancaria cuenta;

    public ConsultarSaldoCommand(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void ejecutar() {
        System.out.printf("[Consultar] %s: saldo %.2f%n", cuenta.getTitular(), cuenta.consultar());
    }

    @Override
    public void deshacer() {
    }
}
