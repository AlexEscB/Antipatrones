package rescate.evaluacion;

public class EvaluadorVisas {

    public ResultadoEvaluacion evaluar(int codigo, int edad) {
        TipoVisa tipo = TipoVisa.desdeCodigo(codigo);
        if (tipo == null) {
            return ResultadoEvaluacion.rechazada("el tipo de visa " + codigo
                    + " no existe, use TURISTA, ESTUDIANTE o TRABAJO");
        }
        return evaluar(tipo, edad);
    }

    public ResultadoEvaluacion evaluar(TipoVisa tipo, int edad) {
        if (tipo.admite(edad)) {
            return ResultadoEvaluacion.aprobada(tipo);
        }
        return ResultadoEvaluacion.rechazada("la edad " + edad + " esta fuera del rango "
                + tipo.getEdadMinima() + " a " + tipo.getEdadMaxima() + " de la visa " + tipo);
    }
}
