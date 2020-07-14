package readability;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print(checkTextDifficulty(new Scanner(System.in).nextLine()));
    }

    private static String checkTextDifficulty(String text) {
        return text.length() <= 100 ? "EASY" : "HARD";
    }
}
