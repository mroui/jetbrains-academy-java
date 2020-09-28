package solver;

public class Row {

    private final LinearEquation equation;
    private double result;

    public Row(LinearEquation equation, double result) {
        this.equation = equation;
        this.result = result;
    }

    public double get(int i) {
        return equation.c(i);
    }

    public void set(int i, double value) {
        equation.setC(i, value);
    }

    public double result() {
        return result;
    }

    public void result(double result) {
        this.result = result;
    }

    public int size() {
        return equation.cSize();
    }

    public Row copy() {
        return new Row(equation.copy(), result);
    }
}
