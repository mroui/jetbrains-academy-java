package flashcards;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlashcardsSet {

    public static Scanner IN = new Scanner(System.in);
    private final List<Flashcard> flashcards;

    public FlashcardsSet() {
        flashcards = new ArrayList<>();
        read();
    }

    private void read() {
        try {
            System.out.println("Input the number of cards:");
            int amount = Integer.parseInt(IN.nextLine());
            for (int i = 0; i < amount; i++) {
                System.out.println("Card #" + i + ':');
                String term = IN.nextLine();
                System.out.println("The definiton for card #" + i + ':');
                String definition = IN.nextLine();
                flashcards.add(new Flashcard(term, definition));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void check() {
        for (Flashcard card : flashcards) {
            System.out.println("Print the definition of \"" + card.term() + "\":");
            String definition = IN.nextLine();
            System.out.println(card.isCorrect(definition) ? "Correct!" :
                    "Wrong. The right answer s \"" + card.definition() + "\".");
        }
    }
}
