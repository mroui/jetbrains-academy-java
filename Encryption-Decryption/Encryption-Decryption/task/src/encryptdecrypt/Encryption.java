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

    static String byShift(String message, int shift) {
        StringBuilder encryption = new StringBuilder();
        for (char ch : message.toCharArray())
            if (alphabet.indexOf(ch) != -1) {
                int index = alphabet.indexOf(ch) + shift;
                while (index >= alphabet.length())
                    index %= alphabet.length();
                encryption.append(alphabet.charAt(index));
            } else {
                encryption.append(ch);
            }
        return encryption.toString();
    }
}
