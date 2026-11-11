package rescate.tarifas;

public final class ParametrosTarifa {

    private final double tarifaBaseMensual;
    private final double cargoFijo;

    public ParametrosTarifa(double tarifaBaseMensual, double cargoFijo) {
        this.tarifaBaseMensual = tarifaBaseMensual;
        this.cargoFijo = cargoFijo;
    }

    public double getTarifaBaseMensual() {
        return tarifaBaseMensual;
    }

    public double getCargoFijo() {
        return cargoFijo;
    }
}
