public class TransferirCommand implements Command {
    private final CuentaBancaria origen;
    private final CuentaBancaria destino;
    private final double monto;
    private boolean exitoso;

    public TransferirCommand(CuentaBancaria origen, CuentaBancaria destino, double monto) {
        this.origen = origen;
        this.destino = destino;
        this.monto = monto;
    }

    @Override
    public void ejecutar() {
        exitoso = origen.transferir(monto, destino);
        if (exitoso) {
            System.out.printf("[Transferir] %s -> %s: %.2f | saldos: %.2f / %.2f%n",
                    origen.getTitular(), destino.getTitular(), monto,
                    origen.consultar(), destino.consultar());
        } else {
            System.out.printf("[Transferir] Rechazado: %s -> %s por %.2f%n",
                    origen.getTitular(), destino.getTitular(), monto);
        }
    }

    @Override
    public void deshacer() {
        if (!exitoso) {
            System.out.println("[Transferir] Nada que deshacer");
            return;
        }
        if (destino.transferir(monto, origen)) {
            exitoso = false;
            System.out.printf("[Deshacer transferencia] %s -> %s: %.2f | saldos: %.2f / %.2f%n",
                    destino.getTitular(), origen.getTitular(), monto,
                    origen.consultar(), destino.consultar());
        } else {
            System.out.println("[Deshacer transferencia] No se pudo revertir: fondos insuficientes en el destino");
        }
    }
}
