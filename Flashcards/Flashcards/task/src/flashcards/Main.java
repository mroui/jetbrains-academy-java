package flashcards;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flashcard> flashcards = Flashcard.read();
        Flashcard.check(flashcards);
    }
}
