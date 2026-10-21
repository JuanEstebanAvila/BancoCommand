public class RetirarCommand implements Command {
    private final CuentaBancaria cuenta;
    private final double monto;
    private boolean exitoso;

    public RetirarCommand(CuentaBancaria cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }

    @Override
    public void ejecutar() {
        exitoso = cuenta.retirar(monto);
        if (exitoso) {
            System.out.printf("[Retirar] %s: -%.2f -> saldo %.2f%n",
                    cuenta.getTitular(), monto, cuenta.consultar());
        } else {
            System.out.printf("[Retirar] Rechazado: %s no puede retirar %.2f (saldo %.2f)%n",
                    cuenta.getTitular(), monto, cuenta.consultar());
        }
    }

    @Override
    public void deshacer() {
        if (!exitoso) {
            System.out.println("[Retirar] Nada que deshacer");
            return;
        }
        cuenta.depositar(monto);
        exitoso = false;
        System.out.printf("[Deshacer retiro] %s: +%.2f -> saldo %.2f%n",
                cuenta.getTitular(), monto, cuenta.consultar());
    }
}
