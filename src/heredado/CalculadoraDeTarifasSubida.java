package heredado;

public class CalculadoraDeTarifasSubida {

    public double visaTurista(int meses) {
        double total = 60 * meses;
        total = total + 10;
        return total;
    }

    public double visaEstudiante(int meses) {
        double total = 60 * meses;
        total = total + 10;
        return total * 0.5;
    }

    public double visaTrabajo(int meses) {
        double total = 60 * meses;
        total = total + 10;
        return total * 1.5;
    }
}
