package correcter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String message = new Scanner(System.in).nextLine();
        String encrypted = triple(message);
        String withErrors = emulateError(encrypted);
        String decrypted = decrypt(withErrors);
        System.out.println(message + '\n' + encrypted + '\n' + withErrors + '\n' + decrypted);
    }

    private static String decrypt(String message) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < message.length(); i += 3)
            if (message.charAt(i) == message.charAt(i + 1) || message.charAt(i) == message.charAt(i + 2))
                result.append(message.charAt(i));
            else if (message.charAt(i + 1) == message.charAt(i + 2))
                result.append(message.charAt(i + 1));
        return result.toString();
    }

    private static String triple(String message) {
        StringBuilder result = new StringBuilder();
        for (char ch : message.toCharArray())
            result.append(ch).append(ch).append(ch);
        return result.toString();
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
