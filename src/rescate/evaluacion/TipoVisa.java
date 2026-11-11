package rescate.evaluacion;

public enum TipoVisa {
    TURISTA(1, 18, Integer.MAX_VALUE),
    ESTUDIANTE(2, 16, 30),
    TRABAJO(3, 18, 65);

    private final int codigo;
    private final int edadMinima;
    private final int edadMaxima;

    TipoVisa(int codigo, int edadMinima, int edadMaxima) {
        this.codigo = codigo;
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public int getEdadMaxima() {
        return edadMaxima;
    }

    public boolean admite(int edad) {
        return edad >= edadMinima && edad <= edadMaxima;
    }

    public static TipoVisa desdeCodigo(int codigo) {
        for (TipoVisa tipo : values()) {
            if (tipo.codigo == codigo) {
                return tipo;
            }
        }
        return null;
    }
}
