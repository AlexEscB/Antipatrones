package rescate.cancelacion;

public class AvisoCorreo implements ObservadorCancelacion {

    @Override
    public void alCancelar(String titular) {
        System.out.println("[Rescate] Correo: cancelacion notificada a " + titular);
    }
}
