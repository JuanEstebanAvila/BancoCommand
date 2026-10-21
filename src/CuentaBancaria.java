public class CuentaBancaria {
    private final String titular;
    private double saldo;

    public CuentaBancaria(String titular, double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor que cero");
        }
        saldo += monto;
    }

    public boolean retirar(double monto) {
        if (monto <= 0 || monto > saldo) {
            return false;
        }
        saldo -= monto;
        return true;
    }

    public boolean transferir(double monto, CuentaBancaria destino) {
        if (destino == null || destino == this) {
            return false;
        }
        if (!retirar(monto)) {
            return false;
        }
        destino.depositar(monto);
        return true;
    }

    public double consultar() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }
}
