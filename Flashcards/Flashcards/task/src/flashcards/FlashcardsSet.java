package flashcards;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static flashcards.Application.*;

public class FlashcardsSet {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final List<Flashcard> flashcards;

    public FlashcardsSet() {
        flashcards = new ArrayList<>();
    }

    public void read() {
        String definition, term;
        try {
            println("Input the number of cards:");
            int amount = Integer.parseInt(IN.nextLine());
            out(amount + NL);
            for (int i = 1; i <= amount; i++) {
                println("Card #" + i + ':');
                term = IN.nextLine();
                out(term + NL);
                while (!isTermUnique(term)) {
                    println("The card \"" + term + "\" already exists. Try again:");
                    term = IN.nextLine();
                    out(term + NL);
                }
                println("The definition for card #" + i + ':');
                definition = IN.nextLine();
                out(definition + NL);
                while (!isDefinitionUnique(definition)) {
                    println("The definition \"" + definition + "\" already exists. Try again:");
                    definition = IN.nextLine();
                    out(definition + NL);
                }
                flashcards.add(new Flashcard(term, definition));
                println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
            }
        } catch (Exception e) {
            println(e.toString());
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
        println("How many times to ask?");
        try {
            int repetitions = Integer.parseInt(IN.nextLine());
            out(repetitions + NL);
            if (flashcards.isEmpty()) {
                println("Flashcards set is empty.");
                return;
            }
            for (int i = 0; i < repetitions; i++) {
                int random = ThreadLocalRandom.current().nextInt(0, flashcards.size());
                Flashcard card = flashcards.get(random);
                println("Print the definition of \"" + card.term() + "\":");
                String definition = IN.nextLine();
                out(definition + NL);
                if (card.isCorrect(definition))
                    println("Correct!");
                else if (isDefinitionUnique(definition)) {
                    card.addMistake();
                    println("Wrong. The right answer is \"" + card.definition() + "\".");
                } else {
                    card.addMistake();
                    println("Wrong. The right answer is \"" + card.definition() + "\", but your definition is correct " +
                            "for \"" + getTermOfDefinition(definition) + "\".");
                }
            }
        } catch (Exception e) {
            println(e.toString());
        }
    }

    public void add() {
        println("The card:");
        String term = IN.nextLine();
        out(term + NL);
        if (isTermUnique(term)) {
            println("The definition of the card:");
            String definition = IN.nextLine();
            out(definition + NL);
            if (isDefinitionUnique(definition)) {
                flashcards.add(new Flashcard(term, definition));
                println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
            } else println("The definition \"" + definition + "\" already exists.");
        } else println("The card \"" + term + "\" already exists.");
    }

    private void add(List<Flashcard> list) {
        for (Flashcard card : list) {
            if (isTermUnique(card.term()))
                flashcards.add(card);
            else flashcards.stream().filter(f -> f.term().equals(card.term()))
                    .forEach(f -> {
                        f.setDefinition(card.definition());
                        f.setMistakes(card.mistakes());
                    });
        }
    }

    public void remove() {
        println("The card:");
        String term = IN.nextLine();
        out(term + NL);
        if (!isTermUnique(term)) {
            flashcards.removeIf(f -> f.term().equals(term));
            println("The card has been removed.");
        } else println("Can't remove \"" + term + "\": there is no such card.");
    }

    public void exportToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(gson.toJson(this));
            println(flashcards.size() + " cards have been saved.");
        } catch (Exception e) {
            println(e.toString());
        }
    }

    public void exportToFile() {
        println("File name:");
        String filename = IN.nextLine();
        out(filename + NL);
        exportToFile(filename);
    }

    public void importFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            FlashcardsSet loaded = gson.fromJson(reader, FlashcardsSet.class);
            println(loaded.flashcards.size() + " cards have been loaded.");
            add(loaded.flashcards);
        } catch (FileNotFoundException e) {
            println("File not found.");
        } catch (Exception e) {
            println(e.toString());
        }
    }

    public void importFromFile() {
        println("File name:");
        String filename = IN.nextLine();
        out(filename + NL);
        importFromFile(filename);
    }

    public void resetStats() {
        flashcards.forEach(Flashcard::resetMistakes);
        println("Card statistics have been reset.");
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
            if (sorted.stream().filter(f -> f.mistakes() == maxCard.mistakes()).count() == 1) {
                println("The hardest card is \"" + maxCard.term() + "\". You have " +
                        maxCard.mistakes() + " errors answering it.");
            } else {
                print("The hardest cards are:");
                sorted.stream().filter(f -> f.mistakes() == maxCard.mistakes()).forEach(f -> print(" \"" + f.term() + '"'));
                println(". You have " + maxCard.mistakes() + " errors answering them.");
            }
        } else println("There are no cards with errors.");
    }

    public void log() {
        println("File name:");
        String filename = IN.nextLine();
        out(filename + NL);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(log.toString());
            println("The log has been saved.");
        } catch (Exception e) {
            println(e.toString());
        }
    }
}
