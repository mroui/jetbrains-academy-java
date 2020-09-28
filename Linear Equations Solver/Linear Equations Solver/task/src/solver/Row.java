package solver;

public class Row {

    private LinearEquation equation;
    private double result;

    public Row(LinearEquation equation, double result) {
        this.equation = equation;
        this.result = result;
    }
}
