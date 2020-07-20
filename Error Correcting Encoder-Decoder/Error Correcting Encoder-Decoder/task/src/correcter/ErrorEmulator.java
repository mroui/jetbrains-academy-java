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

    public static String emulateErrorOnBit(String binary) {
        StringBuilder result = new StringBuilder(binary);
        for (int i = 0; i < binary.length(); i += 8) {
            int index = random(i, i + 7);
            result.setCharAt(index, result.charAt(index) == '1' ? '0' : '1');
        }
        return result.toString();
    }

    public static String decryptErrorOnBitParity(String binary) {
        StringBuilder result = new StringBuilder(binary);
        for (int i = 0; i < binary.length(); i += 8) {
            int indexError = 0;
            int parity = 0;
            for (int j = 0; j < 6; j += 2) {
                if (result.charAt(i + j) != result.charAt(i + j + 1))
                    indexError = i + j;
                else
                    parity ^= result.charAt(i + j);
            }
            int parityBit = Integer.parseInt(String.valueOf(result.charAt(i + 6)));
            String errorBits = parity == parityBit ? "00" : "11";
            result.replace(indexError, indexError + 2, errorBits);
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
