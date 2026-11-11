package rescate.tarifas;

public class TarifaTrabajo implements EstrategiaTarifa {

    private static final double RECARGO_TRABAJO = 1.5;

    @Override
    public double calcular(int meses, ParametrosTarifa parametros) {
        double total = parametros.getTarifaBaseMensual() * meses + parametros.getCargoFijo();
        return total * RECARGO_TRABAJO;
    }
}
