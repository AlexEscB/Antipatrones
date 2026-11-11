package heredado;

public class OficinaDeTodo {

    public void procesar(String nombre, String pasaporte, double precio) {
        if (pasaporte == null || pasaporte.length() < 6) {
            System.out.println("Pasaporte invalido");
            return;
        }
        System.out.println("Cobrando $" + precio + " a " + nombre);
        System.out.println("Guardando expediente de " + nombre);
        System.out.println("Correo para " + nombre + ": su visa va en camino");
    }
}
