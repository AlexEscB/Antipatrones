package rescate.cancelacion;

public class AgendaCitas implements ObservadorCancelacion {

    @Override
    public void alCancelar(String titular) {
        System.out.println("[Rescate] Citas: cupo liberado de " + titular);
    }
}
