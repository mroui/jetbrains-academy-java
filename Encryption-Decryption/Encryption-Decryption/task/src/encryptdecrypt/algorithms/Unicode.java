package encryptdecrypt.algorithms;

public class Unicode extends Shift {

    public Unicode(String message, boolean mode, String input, String output, int shift) {
        super(message, mode, input, output, shift);
    }

    @Override
    String encryptDecrypt() {
        StringBuilder result = new StringBuilder();
        for (char ch : message.toCharArray())
            result.append((char) fixIndex(isEncryption ? ch + shift : ch - shift));
        return result.toString();
    }

    //scope of chars on ASCII table <32, 126>
    @Override
    int fixIndex(int i) {
        final int CHARS_SCOPE = 126 - 32 + 1;
        int index = i;
        while (index < 32)
            index += CHARS_SCOPE;
        while (index > 126)
            index -= CHARS_SCOPE;
        return index;
    }
}
