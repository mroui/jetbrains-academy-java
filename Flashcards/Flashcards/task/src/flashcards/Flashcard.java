package flashcards;

public class Flashcard {

    private String term;
    private String definition;

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
}
