package correcter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(emulateError(new Scanner(System.in).nextLine()));
    }

    private static String emulateError(String text) {
        List<Character> alphabet = getAlphabet();
        StringBuilder result = new StringBuilder(text);
        for (int i = 0; i < text.length(); i += 3)
            result.setCharAt(i + random(0, 2), alphabet.get(random(0, alphabet.size())));
        return result.toString();
    }

    private static int random(int min, int max) {
        return (int) (Math.random() * (max - min - 1)) + min;
    }

    private static List<Character> getAlphabet() {
        List<Character> alphabet = new ArrayList<>();
        for (char i = 'A'; i <= 'Z'; i++)
            alphabet.add(i);
        for (char i = 'a'; i <= 'z'; i++)
            alphabet.add(i);
        for (char i = '0'; i < '9'; i++)
            alphabet.add(i);
        alphabet.add(' ');
        return alphabet;
    }
}
