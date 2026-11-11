package rescate.cancelacion;

public class CajaPagos implements ObservadorCancelacion {

    @Override
    public void alCancelar(String titular) {
        System.out.println("[Rescate] Pagos: reembolso generado para " + titular);
    }
}
