package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static final List<Character> vowels = Arrays.asList('A', 'a', 'O', 'o', 'I', 'i', 'U', 'u', 'Y', 'y', 'E', 'e');

    public static void main(String[] args) {
        try {
            analyzeText(Files.readString(Paths.get(args[0])));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void analyzeText(String text) {
        int sentencesAmount = text.split("[.?!] ").length;
        int wordsAmount = text.split(" ").length;
        int charsAmount = text.replaceAll("\\s", "").length();

        int[] syllablesResult = calculateSyllables(text);
        int syllablesAmount = syllablesResult[0];
        int polysyllablesAmount = syllablesResult[1];

        System.out.println("The text is:\n" + text + '\n');
        System.out.println(
                "Words: " + wordsAmount +
                "\nSentences: " + sentencesAmount +
                "\nCharacters: " + charsAmount +
                "\nSyllables: " + syllablesAmount +
                "\nPolysyllables: " + polysyllablesAmount);

        calculateReadabilityScore(charsAmount, syllablesAmount, polysyllablesAmount, wordsAmount, sentencesAmount);
    }

    private static void calculateReadabilityScore(int chars, int syllables, int polysyllables, int words, int sentences) {
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String option = new Scanner(System.in).next();
        System.out.println();
        switch (option.toUpperCase()) {
            case "ARI":
                ari(chars, words, sentences);
                break;
            case "FK":
                fk(words, syllables, sentences);
                break;
            case "SMOG":
                smog(polysyllables, sentences);
                break;
            case "CL":
                cl(chars, words, sentences);
                break;
            case "ALL":
                double score = 0;
                score += ari(chars, words, sentences);
                score += fk(words, syllables, sentences);
                score += smog(polysyllables, sentences);
                score += cl(chars, words, sentences);
                score /= 4;
                System.out.print("This text should be understood in average by " + String.format("%.2f", score) + " year olds.");
                break;
        }
    }

    private static int[] calculateSyllables(String text) {
        int syllablesAmount = 0;
        int polysyllablesAmount = 0;
        for (String word : text.split("[.,!? ^0-9]+")) {
            int vowelsAmount = 0;
            for (int i = 0; i < word.length() - 1; i++) {
                if (vowels.contains(word.charAt(i)) && !vowels.contains(word.charAt(i + 1))) {
                    vowelsAmount++;
                    ++i;
                }
            }
            if (vowels.subList(0, vowels.size() - 2).contains(word.charAt(word.length() - 1)))
                vowelsAmount++;
            syllablesAmount += vowelsAmount == 0 ? 1 : vowelsAmount;
            polysyllablesAmount += vowelsAmount > 2 ? 1 : 0;
        }
        return new int[] {syllablesAmount, polysyllablesAmount};
    }

    private static int calculateTextScore(int score) {
        return score < 3 ? score + 5 : score < 13 ? score + 6 : 24;
    }

    private static void printScore(double score) {
        System.out.println(String.format("%.2f", score) + " (about " + calculateTextScore((int) score) + " year olds).");
    }

    private static double cl(int chars, int words, int sentences) {
        double score = 0.0588 * chars / words * 100 - 0.296 * sentences / words * 100 - 15.8;
        System.out.print("Coleman–Liau index: ");
        printScore(score);
        return score;
    }

    private static double smog(int polysyllables, int sentences) {
        double score = 1.043 * Math.sqrt(polysyllables * 30.0 / sentences) + 3.1291;
        System.out.print("Simple Measure of Gobbledygook: ");
        printScore(score);
        return score;
    }

    private static double fk(int words, int syllables, int sentences) {
        double score = 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
        System.out.print("Flesch–Kincaid readability tests: ");
        printScore(score);
        return score;
    }

    private static double ari(int chars, int words, int sentences) {
        double score = 4.71 * chars / words + 0.5 * words / sentences - 21.43;
        System.out.print("Automated Readability Index: ");
        printScore(score);
        return score;
    }
}
