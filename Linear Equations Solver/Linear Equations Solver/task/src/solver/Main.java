package solver;

public class Main {
    public static void main(String[] args) {
        Equation e1 = Equation.read();
        Equation e2 = Equation.read();
        if (e1 != null && e2 != null) {
            e1.calculate(e2);
        }
    }
}