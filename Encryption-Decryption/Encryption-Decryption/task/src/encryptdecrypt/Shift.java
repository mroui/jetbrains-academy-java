package encryptdecrypt;

public class Shift {

    static String encrypt(String message, int shift) {
        return encryptDecrypt(message, shift, true);
    }

    static String decrypt(String message, int shift) {
        return encryptDecrypt(message, shift, false);
    }

    private static String encryptDecrypt(String message, int shift, boolean encryption) {
        StringBuilder result = new StringBuilder();
        for (char ch : message.toCharArray())
            result.append((char) fixIndex(encryption ? ch + shift : ch - shift));
        return result.toString();
    }

    //scope of chars on ASCII table <32, 126>
    private static int fixIndex(int i) {
        int index = i;
        while (index < 32)
            index += 126 - 32 + 1;
        while (index > 126)
            index -= 126 - 32 + 1;
        return index;
    }

}
