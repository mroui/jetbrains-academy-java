package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String term = scanner.nextLine();
        String definition = scanner.nextLine();
        Flashcard flashcard = new Flashcard(term, definition);
        String answer = scanner.nextLine();
        System.out.println(flashcard.isCorrect(answer) ? "You answer is right!" : "Your answer is wrong...");
    }
}
