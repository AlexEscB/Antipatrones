package rescate.evaluacion;

public class ResultadoEvaluacion {

    private final boolean aprobada;
    private final String mensaje;

    private ResultadoEvaluacion(boolean aprobada, String mensaje) {
        this.aprobada = aprobada;
        this.mensaje = mensaje;
    }

    public static ResultadoEvaluacion aprobada(TipoVisa tipo) {
        return new ResultadoEvaluacion(true, "APROBADA: la visa " + tipo + " admite la edad indicada");
    }

    public static ResultadoEvaluacion rechazada(String motivo) {
        return new ResultadoEvaluacion(false, "RECHAZADA: " + motivo);
    }

    public boolean isAprobada() {
        return aprobada;
    }

    public String getMensaje() {
        return mensaje;
    }
}
