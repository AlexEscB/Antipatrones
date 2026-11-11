package rescate.tarifas;

import java.util.EnumMap;
import java.util.Map;

import rescate.evaluacion.TipoVisa;

public class CalculadoraTarifas {

    private final Map<TipoVisa, EstrategiaTarifa> estrategias = new EnumMap<>(TipoVisa.class);
    private ParametrosTarifa parametros;

    public CalculadoraTarifas(ParametrosTarifa parametros) {
        this.parametros = parametros;
        estrategias.put(TipoVisa.TURISTA, new TarifaTurista());
        estrategias.put(TipoVisa.ESTUDIANTE, new TarifaEstudiante());
        estrategias.put(TipoVisa.TRABAJO, new TarifaTrabajo());
    }

    public void actualizarParametros(ParametrosTarifa nuevos) {
        this.parametros = nuevos;
    }

    public double calcular(TipoVisa tipo, int meses) {
        return estrategias.get(tipo).calcular(meses, parametros);
    }
}
