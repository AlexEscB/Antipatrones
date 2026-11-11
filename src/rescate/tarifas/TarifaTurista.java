package rescate.tarifas;

public class TarifaTurista implements EstrategiaTarifa {

    @Override
    public double calcular(int meses, ParametrosTarifa parametros) {
        return parametros.getTarifaBaseMensual() * meses + parametros.getCargoFijo();
    }
}
