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
        String definition, term;
        try {
            System.out.println("Input the number of cards:");
            int amount = Integer.parseInt(IN.nextLine());
            for (int i = 1; i <= amount; i++) {
                System.out.println("Card #" + i + ':');
                term = IN.nextLine();
                while (!isTermUnique(term)) {
                    System.out.println("The card \"" + term + "\" already exists. Try again:");
                    term = IN.nextLine();
                }
                System.out.println("The definition for card #" + i + ':');
                definition = IN.nextLine();
                while (!isDefinitionUnique(definition)) {
                    System.out.println("The definition \"" + definition + "\" already exists. Try again:");
                    definition = IN.nextLine();
                }
                flashcards.add(new Flashcard(term, definition));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private boolean isDefinitionUnique(String definition) {
        return flashcards.stream().noneMatch(o -> o.definition().equals(definition));
    }

    private boolean isTermUnique(String term) {
        return flashcards.stream().noneMatch(o -> o.term().equals(term));
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
