package encryptdecrypt;

public class Encryption {

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    static String byOtherSide(String message) {
        StringBuilder encryption = new StringBuilder();
        for (char ch : message.toCharArray())
            encryption.append(alphabet.indexOf(ch) != -1
                    ? alphabet.charAt(alphabet.length() - alphabet.indexOf(ch) - 1)
                    : ch);
        return encryption.toString();
    }
}
