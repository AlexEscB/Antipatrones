package rescate.cancelacion;

import java.util.ArrayList;
import java.util.List;

public class SolicitudVisa {

    public static final String ESTADO_ACTIVA = "ACTIVA";
    public static final String ESTADO_CANCELADA = "CANCELADA";

    private final String titular;
    private final List<ObservadorCancelacion> observadores = new ArrayList<>();
    private String estado = ESTADO_ACTIVA;

    public SolicitudVisa(String titular) {
        this.titular = titular;
    }

    public void suscribir(ObservadorCancelacion observador) {
        observadores.add(observador);
    }

    public void cancelar() {
        estado = ESTADO_CANCELADA;
        for (ObservadorCancelacion observador : observadores) {
            observador.alCancelar(titular);
        }
    }

    public String getEstado() {
        return estado;
    }

    public String getTitular() {
        return titular;
    }
}
