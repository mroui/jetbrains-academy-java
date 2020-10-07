package flashcards;

public class Flashcard {

    private final String term;
    private final String definition;

    public Flashcard(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public String term() {
        return term;
    }

    public String definition() {
        return definition;
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
}
