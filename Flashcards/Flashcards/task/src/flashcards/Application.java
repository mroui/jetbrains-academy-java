package flashcards;


import java.util.Scanner;

public class Application {

    public static final Scanner IN = new Scanner(System.in);
    public static final StringBuilder log = new StringBuilder();
    public static final String NL = System.getProperty("line.separator");
    private String filenameToExport;

    private final FlashcardsSet set;

    public Application() {
        this.set = new FlashcardsSet();
    }

    private void parseArguments(String... args) {
        if (args.length > 0) {
            try {
                for (int i = 0; i < args.length; i += 2) {
                    if (args[i].equals("-import"))
                        set.importFromFile(args[i + 1]);
                    else if (args[i].equals("-export"))
                        filenameToExport = args[i + 1];
                    else println("Unknown argument \"" + args[i] + "\".");
                }
            } catch (Exception e) {
                println("Incorrect arguments given.");
            }
        }
    }

    public void run(String... args) {
        parseArguments(args);
        println("\nInput the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
        String option = IN.nextLine().trim();
        out(option + NL);
        switch (option) {
            case "add":
                set.add();
                break;
            case "remove":
                set.remove();
                break;
            case "import":
                set.importFromFile();
                break;
            case "export":
                set.exportToFile();
                break;
            case "ask":
                set.ask();
                break;
            case "exit":
                println("Bye bye!");
                if (filenameToExport != null) set.exportToFile(filenameToExport);
                return;
            case "log":
                set.log();
                break;
            case "hardest card":
                set.hardestCard();
                break;
            case "reset stats":
                set.resetStats();
                break;
            default:
                println("Unknown command.");
        }
        run();
    }

    public static void println(Object obj) {
        System.out.println(obj);
        out(obj);
        out(NL);
    }

    public static void print(Object obj) {
        System.out.print(obj);
        out(obj);
    }

    public static void out(Object obj) {
        log.append(obj);
    }
}
