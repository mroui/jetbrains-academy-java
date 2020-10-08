package flashcards;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static flashcards.Application.IN;

public class FlashcardsSet {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
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
                if (card.isCorrect(definition))
                    System.out.println("Correct!");
                else if (isDefinitionUnique(definition)) {
                    card.addMistake();
                    System.out.println("Wrong. The right answer is \"" + card.definition() + "\".");
                } else {
                    card.addMistake();
                    System.out.println("Wrong. The right answer is \"" + card.definition() +
                            "\", but your definition is correct " +
                            "for \"" + getTermOfDefinition(definition) + "\".");
                }
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

    private void add(List<Flashcard> list) {
        for (Flashcard card : list) {
            if (isTermUnique(card.term()))
                flashcards.add(card);
            else flashcards.stream().filter(f -> f.term().equals(card.term()))
                    .forEach(f -> f.setDefinition(card.definition()));
        }
    }

    public void remove() {
        System.out.println("The card:");
        String term = IN.nextLine();
        if (!isTermUnique(term)) {
            flashcards.removeIf(f -> f.term().equals(term));
            System.out.println("The card has been removed.");
        } else System.out.println("Can't remove \"" + term + "\": there is no such card.");
    }

    public void exportToFile() {
        System.out.println("File name:");
        String filename = IN.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(gson.toJson(this));
            System.out.println(flashcards.size() + " cards have been saved.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void importFromFile() {
        System.out.println("File name:");
        String filename = IN.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            FlashcardsSet loaded = gson.fromJson(reader, FlashcardsSet.class);
            System.out.println(loaded.flashcards.size() + " cards have been loaded.");
            add(loaded.flashcards);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void resetStats() {
        flashcards.forEach(Flashcard::resetMistakes);
    }

    private boolean anyErrors() {
        for (Flashcard card : flashcards)
            if (card.mistakes() > 0)
                return true;
        return false;
    }

    public void hardestCard() {
        if (anyErrors()) {
            List<Flashcard> sorted = new ArrayList<>(flashcards);
            sorted.sort(Comparator.comparingInt(Flashcard::mistakes));
            Flashcard maxCard = sorted.get(sorted.size() - 1);
            Stream<Flashcard> maxStream = sorted.stream().filter(f -> f.mistakes() == maxCard.mistakes());
            if (maxStream.count() == 1) {
                System.out.println("The hardest card is \"" + maxCard.term() + "\". You have " + maxCard.mistakes() +
                        " errors answering it.");
            } else {
                System.out.print("The hardest cards are:");
                maxStream.forEach(f -> System.out.print(" \"" + f.term() + '"'));
                System.out.println(". You have " + maxCard.mistakes() + " errors answering them.");
            }
        } else System.out.println("There are no cards wth errors.");
    }
}
