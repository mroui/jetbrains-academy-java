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
                if (isTermUnique(term)) {
                    System.out.println("The definiton for card #" + i + ':');
                    String definition = IN.nextLine();
                    if (isDefinitionUnique(definition))
                        flashcards.add(new Flashcard(term, definition));
                    else {
                        System.out.println("The definition \"" + definition + "\"");
                        i--;
                    }
                } else {
                    System.out.println("The term \"" + term + "\" already exists. Try again:");
                    i--;
                }
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
