package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            analyzeText(Files.readString(Paths.get(args[0])));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void analyzeText(String text) {
        System.out.println("The text is:\n" + text + '\n');
        int sentencesAmount = text.split("[.?!] ").length;
        int wordsAmount = text.split(" ").length;
        int charsAmount = text.replaceAll("\\s", "").length();
        double score = 4.71 * charsAmount / wordsAmount + 0.5 * wordsAmount / sentencesAmount - 21.43;
        System.out.println("Words: " + wordsAmount +
                "\nSentences: " + sentencesAmount +
                "\nCharacters: " + charsAmount +
                "\nThe score is: " + String.format("%.2f", score) +
                "\nThis text should be understood by " + calculateTextScore((int) Math.ceil(score)) + " year olds.");
    }

    private static String calculateTextScore(int score) {
        if (score == 13)
            return "18-24";
        else if (score > 13)
            return "24+";
        else {
            int from = score < 4 ? score + 4 : score + 5;
            int to = score < 3 ? score + 5 : score + 6;
            return from + "-" + to;
        }
    }
}
