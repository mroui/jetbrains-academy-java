package encryptdecrypt.algorithms;

public class Shift extends Algorithm {

    private final int shift;

    public Shift(String message, String mode, String input, String output, int shift) {
        super(message, mode, input, output);
        this.shift = shift;
    }

    @Override
    public String encrypt() {
        return encryptDecrypt( true);
    }

    @Override
    public String decrypt() {
        return encryptDecrypt( false);
    }

    private String encryptDecrypt(boolean encryption) {
        StringBuilder result = new StringBuilder();
        for (char ch : message.toCharArray())
            result.append((char) fixIndex(encryption ? ch + shift : ch - shift));
        return result.toString();
    }

    //scope of chars on ASCII table <32, 126>
    private int fixIndex(int i) {
        int index = i;
        while (index < 32)
            index += 126 - 32 + 1;
        while (index > 126)
            index -= 126 - 32 + 1;
        return index;
    }
}
