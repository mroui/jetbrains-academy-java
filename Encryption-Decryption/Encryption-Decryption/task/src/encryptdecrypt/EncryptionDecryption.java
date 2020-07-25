package encryptdecrypt;

public class EncryptionDecryption {

    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    static String byOtherSide(String message) {
        StringBuilder encryption = new StringBuilder();
        for (char ch : message.toCharArray())
            encryption.append(alphabet.indexOf(ch) != -1
                    ? alphabet.charAt(alphabet.length() - alphabet.indexOf(ch) - 1)
                    : ch);
        return encryption.toString();
    }

    static String byShift(String message, int shift, boolean encrypt) {
        StringBuilder encryption = new StringBuilder();
        for (char ch : message.toCharArray()) {
            int index = encrypt ? ch + shift : ch - shift;
            while (index < 32)
                index += 126 - 32 + 1;      //scope of chars on ASCII table <32, 126>
            while (index > 126)
                index -= 126 - 32 + 1;
            encryption.append((char) index);
        }
        return encryption.toString();
    }
}
