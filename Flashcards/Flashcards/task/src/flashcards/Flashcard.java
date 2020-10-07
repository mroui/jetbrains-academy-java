package flashcards;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Flashcard {

    public static Scanner scanner = new Scanner(System.in);
    private final String term;
    private final String definition;

    public Flashcard(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    @Override
    public String toString() {
        return "Card:\n" +
                term + '\n' +
                "Definition:\n" +
                definition;
    }

    public boolean isCorrect(String answer) {
        return definition.equals(answer);
    }

    public static List<Flashcard> read() {
        List<Flashcard> flashcards = new ArrayList<>();
        try {
            System.out.println("Input the number of cards:");
            int amount = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < amount; i++) {
                System.out.println("Card #" + i + ':');
                String term = scanner.nextLine();
                System.out.println("The definiton for card #" + i + ':');
                String definition = scanner.nextLine();
                flashcards.add(new Flashcard(term, definition));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return flashcards;
    }

    public static void check(List<Flashcard> list) {
        for (Flashcard card : list) {
            System.out.println("Print the definition of \"" + card.term + "\":");
            String definition = scanner.nextLine();
            System.out.println(card.isCorrect(definition) ? "Correct!" :
                    "Wrong. The right answer s \"" + card.definition + "\".");
        }
    }
}
