package search;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> baseWords = readWords();
        List<String> wordsToFind = readWords();
        for (String word : wordsToFind)
            System.out.print((baseWords.contains(word) ? baseWords.indexOf(word) + 1 : "Not found") + " ");
    }

    private static List<String> readWords() {
        return Arrays.asList(new Scanner(System.in).nextLine().split("\\s"));
    }
}
