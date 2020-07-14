package readability;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(checkTextDifficulty(new Scanner(System.in).nextLine()));
    }

    public static String checkTextDifficulty(String text) {
        String[] sentences = text.split("[.?!]");
        int words = 0;
        for (String s : sentences)
            words += s.split("\\s+").length;
        return words / sentences.length > 10 ? "HARD" : "EASY";
    }
}
