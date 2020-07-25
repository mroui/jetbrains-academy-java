package encryptdecrypt;

public class Reversed {

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    static String encrypt(String message) {
        StringBuilder encryption = new StringBuilder();
        for (char ch : message.toCharArray())
            encryption.append(alphabet.indexOf(ch) != -1
                    ? alphabet.charAt(alphabet.length() - alphabet.indexOf(ch) - 1)
                    : ch);
        return encryption.toString();
    }

    static String decrypt(String message) {
        return encrypt(message);
    }

}
