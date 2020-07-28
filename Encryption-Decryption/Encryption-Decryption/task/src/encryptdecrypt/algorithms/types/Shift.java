package encryptdecrypt.algorithms.types;

public class Shift extends Algorithm {

    final int shift;

    public Shift(String message, boolean mode, String input, String output, int shift) {
        super(message, mode, input, output);
        this.shift = shift;
    }

    @Override
    public String encrypt() {
        return encryptDecrypt();
    }

    @Override
    public String decrypt() {
        return encryptDecrypt();
    }

    String encryptDecrypt() {
        StringBuilder result = new StringBuilder();
        for (char ch : message.toCharArray())
            if (ch >= 65 && ch <= 90 || ch >= 97 && ch <= 122)
                result.append((char) fixIndex(isEncryption ? ch + shift : ch - shift));
            else
                result.append(ch);
        return result.toString();
    }

    //scope of chars on ASCII table <65, 90> u <97, 122>
    int fixIndex(int i) {
        int ALPHABET_SCOPE = 26;
        char ch = (char) (isEncryption ? i - shift : i + shift);
        int index = i;

        if (ch >= 65 && ch <= 90) {
            while (index < 65)
                index += ALPHABET_SCOPE;
            while (index > 90)
                index -= ALPHABET_SCOPE;
        } else {
            while (index < 97)
                index += ALPHABET_SCOPE;
            while (index > 122)
                index -= ALPHABET_SCOPE;
        }
        return index;
    }
}
