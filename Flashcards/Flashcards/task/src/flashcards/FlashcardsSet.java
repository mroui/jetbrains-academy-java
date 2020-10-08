package flashcards;

import java.util.ArrayList;
import java.util.List;

import static flashcards.Application.IN;

public class FlashcardsSet {

    private final List<Flashcard> flashcards;

    public FlashcardsSet() {
        flashcards = new ArrayList<>();
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

    private String getTermOfDefinition(String definition) {
        return flashcards.stream().filter(o -> o.definition().equals(definition)).findFirst()                                        // Get Optional<Country>
                .map(Flashcard::term).orElse(null);
    }

    public void check() {
        for (Flashcard card : flashcards) {
            System.out.println("Print the definition of \"" + card.term() + "\":");
            String definition = IN.nextLine();
            System.out.println(card.isCorrect(definition) ? "Correct!" :
                    isDefinitionUnique(definition) ? "Wrong. The right answer is \"" + card.definition() + "\"."
                            : "Wrong. The right answer is \"" + card.definition() + "\", but your definition is correct " +
                            "for \"" + getTermOfDefinition(definition) + "\".");
        }
    }
}
