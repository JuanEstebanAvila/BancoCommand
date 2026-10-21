import java.util.ArrayDeque;
import java.util.Deque;

public class CajeroAutomatico {
    private final Deque<Command> historial = new ArrayDeque<>();

    public void ejecutarComando(Command comando) {
        comando.ejecutar();
        historial.push(comando);
    }

    public void deshacerUltimo() {
        if (historial.isEmpty()) {
            System.out.println("[Cajero] No hay operaciones para deshacer");
            return;
        }
        historial.pop().deshacer();
    }
}
