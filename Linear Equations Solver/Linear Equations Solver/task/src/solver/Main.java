package solver;

public class Main {

    public static void main(String[] args) {
        run(args);
    }

    private static void run(String[] args) {
        EquationSystem system = null;
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-in":
                    Matrix matrix = Matrix.read(args[i + 1]);
                    if (matrix != null) {
                        system = new EquationSystem(matrix);
                        system.solve();
                    }
                    break;
                case "-out":
                    if (system != null)
                        system.saveResultsToFile(args[i + 1]);
                    break;
                default:
                    System.out.println("Unknown argument: " + args[i]);
                    break;
            }
        }
    }
}