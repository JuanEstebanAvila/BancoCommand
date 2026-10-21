public class DepositarCommand implements Command {
    private final CuentaBancaria cuenta;
    private final double monto;
    private boolean ejecutado;

    public DepositarCommand(CuentaBancaria cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }

    @Override
    public void ejecutar() {
        try {
            cuenta.depositar(monto);
            ejecutado = true;
            System.out.printf("[Depositar] %s: +%.2f -> saldo %.2f%n",
                    cuenta.getTitular(), monto, cuenta.consultar());
        } catch (IllegalArgumentException e) {
            ejecutado = false;
            System.out.println("[Depositar] Rechazado: " + e.getMessage());
        }
    }

    @Override
    public void deshacer() {
        if (!ejecutado) {
            System.out.println("[Depositar] Nada que deshacer");
            return;
        }
        if (cuenta.retirar(monto)) {
            ejecutado = false;
            System.out.printf("[Deshacer depósito] %s: -%.2f -> saldo %.2f%n",
                    cuenta.getTitular(), monto, cuenta.consultar());
        } else {
            System.out.println("[Deshacer depósito] No se pudo revertir: fondos insuficientes");
        }
    }
}
