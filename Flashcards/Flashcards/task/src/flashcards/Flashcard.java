package flashcards;

public class Flashcard {

    private final String term;
    private String definition;
    private int mistakes;

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

    public int mistakes() {
        return mistakes;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void addMistake() {
        this.mistakes++;
    }

    public void resetMistakes() {
        this.mistakes = 0;
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
