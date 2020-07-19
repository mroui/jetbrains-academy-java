package correcter;

import java.util.ArrayList;
import java.util.List;

public abstract class ErrorEmulator {

    public static String emulateErrorOnChar(String text) {
        List<Character> alphabet = getAlphabet();
        StringBuilder result = new StringBuilder(text);
        for (int i = 0; i < text.length(); i += 3) {
            int randomIndex = i + random(0, 2);
            char randomChar = text.charAt(randomIndex);
            while (text.charAt(randomIndex) == randomChar)
                randomChar = alphabet.get(random(0, alphabet.size()));
            result.setCharAt(randomIndex, randomChar);
        }
        return result.toString();
    }

    public static String emulateErrorOnBit(String text) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            StringBuilder binary = new StringBuilder(Integer.toBinaryString(ch));
            int index = random(0, 7);
            binary.setCharAt(index, binary.charAt(index) == '1' ? '0' : '1');
            result.append((char) Integer.parseInt(binary.toString(), 2));
        }
        return result.toString();
    }

    public static String decryptTriple(String message) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < message.length(); i += 3)
            if (message.charAt(i) == message.charAt(i + 1) || message.charAt(i) == message.charAt(i + 2))
                result.append(message.charAt(i));
            else if (message.charAt(i + 1) == message.charAt(i + 2))
                result.append(message.charAt(i + 1));
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
