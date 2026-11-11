package rescate.tarifas;

public class TarifaEstudiante implements EstrategiaTarifa {

    private static final double PROPORCION_ESTUDIANTE = 0.5;

    @Override
    public double calcular(int meses, ParametrosTarifa parametros) {
        double total = parametros.getTarifaBaseMensual() * meses + parametros.getCargoFijo();
        return total * PROPORCION_ESTUDIANTE;
    }
}
