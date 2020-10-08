package flashcards;


import java.util.Scanner;

public class Application {

    public static Scanner IN = new Scanner(System.in);
    private final FlashcardsSet set;

    public Application() {
        this.set = new FlashcardsSet();
    }

    public void run() {
        System.out.println("\nInput the action (add, remove, import, export, ask, exit):");
        switch (IN.nextLine().trim()) {
            case "add":
                //todo
                break;
            case "remove":
                //todo
                break;
            case "import":
                //todo
                break;
            case "export":
                //todo
                break;
            case "ask":
                //todo
                break;
            case "exit":
                System.out.println("Bye bye!");
                return;
        }
        run();
    }

}
