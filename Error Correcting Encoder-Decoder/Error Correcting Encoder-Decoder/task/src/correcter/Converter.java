package correcter;

public abstract class Converter {

    public static String convertToBin(String text, int bitsAmount) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            String binary = Integer.toBinaryString(ch);
            int length = binary.length();
            while (length % bitsAmount != 0) {
                result.append('0');
                length++;
            }
            result.append(binary);
        }
        return result.toString();
    }

    //-1 for normal text, radix > 1 for number systems
    public static String convertToHex(String text, int radix, int bitsAmount) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += bitsAmount) {
            if (radix != -1) {
                int decimal = i + bitsAmount >= text.length()
                        ? Integer.parseInt(String.valueOf(text.charAt(i)))
                        : Integer.parseInt(text, i, i + bitsAmount, radix);
                String hex = Integer.toHexString(decimal);
                result.append(hex.length() == 1 ? '0' + hex : hex);
            } else
                result.append(Integer.toHexString(text.charAt(i)));
        }
        return result.toString();
    }

    public static String getWithSpaces(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(text.charAt(i));
            if ((i + 1) % shift == 0)
                result.append(" ");
        }
        return result.toString();
    }

    public static String repeatCharacters(String message, int howMany) {
        StringBuilder result = new StringBuilder();
        for (char ch : message.toCharArray())
            result.append(String.valueOf(ch).repeat(Math.max(0, howMany)));
        return result.toString();
    }

}
