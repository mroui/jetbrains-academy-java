package encryptdecrypt.algorithms;

public class Reversed extends Algorithm {

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public Reversed(String message, String mode, String input, String output) {
        super(message, mode, input, output);
    }

    @Override
    public String encrypt() {
        StringBuilder encryption = new StringBuilder();
        for (char ch : message.toCharArray())
            encryption.append(alphabet.indexOf(ch) != -1
                    ? alphabet.charAt(alphabet.length() - alphabet.indexOf(ch) - 1)
                    : ch);
        return encryption.toString();
    }

    @Override
    public String decrypt() {
        return encrypt();
    }
}
