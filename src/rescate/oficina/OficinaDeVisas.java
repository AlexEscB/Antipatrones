package rescate.oficina;

public class OficinaDeVisas {

    private final ValidadorPasaporte validador;
    private final Cajero cajero;
    private final ArchivoExpedientes expedientes;
    private final EnviadorCorreos correos;

    public OficinaDeVisas(ValidadorPasaporte validador, Cajero cajero,
                          ArchivoExpedientes expedientes, EnviadorCorreos correos) {
        this.validador = validador;
        this.cajero = cajero;
        this.expedientes = expedientes;
        this.correos = correos;
    }

    public boolean tramitar(String nombre, String pasaporte, double precio) {
        if (!validador.esValido(pasaporte)) {
            System.out.println("[Rescate] Pasaporte invalido para " + nombre);
            return false;
        }
        cajero.cobrar(nombre, precio);
        expedientes.guardar(nombre);
        correos.notificarVisaEnCamino(nombre);
        return true;
    }
}
