package solver;

public class Main {

    public static void main(String[] args) {
        run(args);
    }

    private static void run(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-in":
                    //todo
                    break;
                case "-out":
                    //todo
                    break;
                default:
                    System.out.println("Unknown argument: " + args[i]);
                    break;
            }
        }
    }

}