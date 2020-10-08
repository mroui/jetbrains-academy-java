package flashcards;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static flashcards.Application.IN;

public class FlashcardsSet {

    private final List<Flashcard> flashcards;

    public FlashcardsSet() {
        flashcards = new ArrayList<>();
    }

    public void read() {
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
                System.out.println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
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

    public void ask() {
        System.out.println("How many times to ask?");
        try {
            int repetitions = Integer.parseInt(IN.nextLine());
            if (flashcards.isEmpty()) {
                System.out.println("Flashcards set is empty.");
                return;
            }
            for (int i = 0; i < repetitions; i++) {
                int random = ThreadLocalRandom.current().nextInt(0, flashcards.size());
                Flashcard card = flashcards.get(random);
                System.out.println("Print the definition of \"" + card.term() + "\":");
                String definition = IN.nextLine();
                System.out.println(card.isCorrect(definition) ? "Correct!" :
                        isDefinitionUnique(definition) ? "Wrong. The right answer is \"" + card.definition() + "\"."
                                : "Wrong. The right answer is \"" + card.definition() + "\", but your definition is correct " +
                                "for \"" + getTermOfDefinition(definition) + "\".");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void add() {
        System.out.println("The card:");
        String term = IN.nextLine();
        if (isTermUnique(term)) {
            System.out.println("The definition of the card:");
            String definition = IN.nextLine();
            if (isDefinitionUnique(definition)) {
                flashcards.add(new Flashcard(term, definition));
                System.out.println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
            } else System.out.println("The definition \"" + definition + "\" already exists.");
        } else System.out.println("The card \"" + term + "\" already exists.");
    }

    public void remove() {
        System.out.println("The card:");
        String term = IN.nextLine();
        if (!isTermUnique(term)) {
            flashcards.removeIf(f -> f.term().equals(term));
            System.out.println("The card has been removed.");
        } else System.out.println("Can't remove \"" + term + "\": there is no such card.");
    }
}
