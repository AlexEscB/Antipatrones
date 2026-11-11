package rescate.oficina;

public class ValidadorPasaporte {

    private static final int LONGITUD_MINIMA = 6;

    public boolean esValido(String pasaporte) {
        return pasaporte != null && pasaporte.length() >= LONGITUD_MINIMA;
    }
}
