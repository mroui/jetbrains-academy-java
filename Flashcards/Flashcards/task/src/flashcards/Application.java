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
                set.add();
                break;
            case "remove":
                set.remove();
                break;
            case "import":
                //todo
                break;
            case "export":
                //todo
                break;
            case "ask":
                set.ask();
                break;
            case "exit":
                System.out.println("Bye bye!");
                return;
            default:
                System.out.println("Unknown command.");
        }
        run();
    }

}
