package app;

import heredado.CalculadoraDeTarifas;
import heredado.CalculadoraDeTarifasSubida;
import heredado.EvaluadorMagico;
import heredado.OficinaDeTodo;
import heredado.SolicitudAcoplada;
import rescate.cancelacion.AgendaCitas;
import rescate.cancelacion.AvisoCorreo;
import rescate.cancelacion.CajaPagos;
import rescate.cancelacion.SolicitudVisa;
import rescate.evaluacion.EvaluadorVisas;
import rescate.evaluacion.TipoVisa;
import rescate.oficina.ArchivoExpedientes;
import rescate.oficina.Cajero;
import rescate.oficina.EnviadorCorreos;
import rescate.oficina.OficinaDeVisas;
import rescate.oficina.ValidadorPasaporte;
import rescate.tarifas.CalculadoraTarifas;
import rescate.tarifas.ParametrosTarifa;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== TALLER DE ANTIPATRONES: LA OFICINA DE VISAS DEL CAOS ===");
        caso1GodObject();
        caso2CopyPaste();
        caso3MagicNumbers();
        caso4AcoplamientoFuerte();
        caso5Integracion();
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.println(texto);
        System.out.println("------------------------------------------------------------");
    }

    private static void caso1GodObject() {
        titulo("CASO 1 - God Object: tramitar la visa de un viajero");

        System.out.println(">> VERSION HEREDADA (OficinaDeTodo lo hace todo):");
        OficinaDeTodo ramiro = new OficinaDeTodo();
        ramiro.procesar("Luna Sandoval", "AB123456", 160);

        System.out.println();
        System.out.println(">> VERSION RESCATADA (una clase por responsabilidad):");
        OficinaDeVisas oficina = new OficinaDeVisas(
                new ValidadorPasaporte(),
                new Cajero(),
                new ArchivoExpedientes(),
                new EnviadorCorreos());
        oficina.tramitar("Luna Sandoval", "AB123456", 160);

        System.out.println();
        System.out.println(">> Ambas aprueban el tramite, pero en la rescatada la salida");
        System.out.println("   muestra quien hace cada tarea y nadie se enferma de Ramiro.");
    }

    private static void caso2CopyPaste() {
        titulo("CASO 2 - Copy-Paste: subir la tarifa base de 50 a 60");

        CalculadoraDeTarifas clonada = new CalculadoraDeTarifas();
        System.out.println(">> VERSION HEREDADA con tarifa base 50 (6 meses):");
        System.out.println("   Turista: $" + clonada.visaTurista(6));
        System.out.println("   Estudiante: $" + clonada.visaEstudiante(6));
        System.out.println("   Trabajo: $" + clonada.visaTrabajo(6));

        System.out.println();
        System.out.println(">> Se ordena subir la tarifa base de 50 a 60...");
        System.out.println(">> VERSION HEREDADA: hay que editar el 50 en cada metodo copiado");
        System.out.println("   Edicion 1 de 3: visaTurista cambia 50 por 60");
        System.out.println("   Edicion 2 de 3: visaEstudiante cambia 50 por 60");
        System.out.println("   Edicion 3 de 3: visaTrabajo cambia 50 por 60");
        CalculadoraDeTarifasSubida clonadaSubida = new CalculadoraDeTarifasSubida();
        System.out.println("   Turista: $" + clonadaSubida.visaTurista(6));
        System.out.println("   Estudiante: $" + clonadaSubida.visaEstudiante(6));
        System.out.println("   Trabajo: $" + clonadaSubida.visaTrabajo(6));

        System.out.println();
        System.out.println(">> VERSION STRATEGY: basta tocar ParametrosTarifa");
        System.out.println("   Edicion 1 de 1: ParametrosTarifa cambia la tarifa base a 60");
        CalculadoraTarifas strategy = new CalculadoraTarifas(new ParametrosTarifa(50, 10));
        strategy.actualizarParametros(new ParametrosTarifa(60, 10));
        System.out.println("   Turista: $" + strategy.calcular(TipoVisa.TURISTA, 6));
        System.out.println("   Estudiante: $" + strategy.calcular(TipoVisa.ESTUDIANTE, 6));
        System.out.println("   Trabajo: $" + strategy.calcular(TipoVisa.TRABAJO, 6));

        System.out.println();
        System.out.println(">> El precio final coincide en ambas, pero una costo 3 ediciones");
        System.out.println("   y la otra solo 1.");
    }

    private static void caso3MagicNumbers() {
        titulo("CASO 3 - Magic Numbers: evaluar tipo 2 con edad 20 y probar el tipo 9");

        EvaluadorMagico magico = new EvaluadorMagico();
        System.out.println(">> VERSION HEREDADA (tipo 2, edad 20): " + magico.evaluar(2, 20));
        System.out.println(">> VERSION HEREDADA (tipo 9, edad 20): " + magico.evaluar(9, 20));
        System.out.println("   Rechazo en silencio: nadie sabe que significa el 9.");

        System.out.println();
        EvaluadorVisas evaluador = new EvaluadorVisas();
        System.out.println(">> VERSION RESCATADA (TipoVisa.ESTUDIANTE, edad 20):");
        System.out.println("   " + evaluador.evaluar(TipoVisa.ESTUDIANTE, 20).getMensaje());
        System.out.println(">> VERSION RESCATADA (tipo 9, edad 20):");
        System.out.println("   " + evaluador.evaluar(9, 20).getMensaje());
        System.out.println(">> El resultado del caso valido es el mismo y el tipo 9 recibe");
        System.out.println("   un mensaje claro en lugar de un rechazo inexplicable.");
    }

    private static void caso4AcoplamientoFuerte() {
        titulo("CASO 4 - Acoplamiento fuerte: cancelar una solicitud");

        System.out.println(">> VERSION HEREDADA (SolicitudAcoplada):");
        SolicitudAcoplada acoplada = new SolicitudAcoplada();
        acoplada.cancelar();
        System.out.println("   Estado final: " + acoplada.getEstado());
        System.out.println("   La caja de pagos nunca se entero: el reembolso se pierde.");

        System.out.println();
        System.out.println(">> VERSION RESCATADA (Observer):");
        SolicitudVisa solicitud = new SolicitudVisa("Eddie Rondon");
        solicitud.suscribir(new AvisoCorreo());
        solicitud.suscribir(new AgendaCitas());
        solicitud.suscribir(new CajaPagos());
        solicitud.cancelar();
        System.out.println("   Estado final: " + solicitud.getEstado());
        System.out.println(">> La solicitud no conoce a los servicios: cada uno se suscribe");
        System.out.println("   y reacciona al evento por su cuenta.");
    }

    private static void caso5Integracion() {
        titulo("CASO 5 - Integracion: viaje completo con la version rescatada");

        String viajero = "Luna Sandoval";
        OficinaDeVisas oficina = new OficinaDeVisas(
                new ValidadorPasaporte(),
                new Cajero(),
                new ArchivoExpedientes(),
                new EnviadorCorreos());
        CalculadoraTarifas tarifas = new CalculadoraTarifas(new ParametrosTarifa(60, 10));
        EvaluadorVisas evaluador = new EvaluadorVisas();

        System.out.println(">> Solicitar y cobrar:");
        double precio = tarifas.calcular(TipoVisa.ESTUDIANTE, 6);
        oficina.tramitar(viajero, "AB123456", precio);

        System.out.println(">> Evaluar:");
        System.out.println("   " + evaluador.evaluar(TipoVisa.ESTUDIANTE, 20).getMensaje());

        System.out.println(">> Cancelar:");
        SolicitudVisa solicitud = new SolicitudVisa(viajero);
        solicitud.suscribir(new AvisoCorreo());
        solicitud.suscribir(new AgendaCitas());
        solicitud.suscribir(new CajaPagos());
        solicitud.cancelar();

        tablaFinal();
    }

    private static void tablaFinal() {
        System.out.println();
        System.out.println("=== TABLA FINAL: ANTIPATRONES CAPTURADOS ===");
        System.out.printf("%-20s | %-46s | %s%n", "Antipatron", "Sintoma", "Solucion");
        System.out.printf("%-20s | %-46s | %s%n",
                "God Object", "Ramiro hace todo en una sola clase",
                "Responsabilidad unica: una clase por tarea");
        System.out.printf("%-20s | %-46s | %s%n",
                "Copy-Paste", "La misma formula copiada en 3 metodos",
                "Strategy: la tarifa base cambia en un solo lugar");
        System.out.printf("%-20s | %-46s | %s%n",
                "Magic Numbers", "tipo == 3 y 0.5 sin nombre ni sentido",
                "Enum TipoVisa y constantes con nombre");
        System.out.printf("%-20s | %-46s | %s%n",
                "Acoplamiento fuerte", "Cancelar exige llamar servicios a mano",
                "Observer: la solicitud solo publica el evento");
    }
}
