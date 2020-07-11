import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int upperCases = scanner.nextInt();
        int lowerCases = scanner.nextInt();
        int digits = scanner.nextInt();
        int length = scanner.nextInt();
        System.out.println(generatePassword(upperCases, lowerCases, digits, length));
    }

    private static String generatePassword(int upperCases, int lowerCases, int digits, int length) {
        StringBuilder password = new StringBuilder();
        fillCharsFromScope(password, 65, 90, upperCases);
        fillCharsFromScope(password, 97, 122, lowerCases);
        fillCharsFromScope(password, 48, 57, digits);
        fillCharsFromScope(password, 65, 90, length - password.length());
        return password.toString();
    }

    public static void fillCharsFromScope(StringBuilder str, int from, int to, int count) {
        int lastChar = str.length() > 0 ? str.charAt(str.length() - 1) : -1;
        int i = lastChar >= from && lastChar <= to ? lastChar - from + 1 : 0;
        for (int loop = 0; loop < count; loop++) {
            str.append((char) (from + i));
            i = i > to - from - 1 ? 0 : ++i;
        }
    }

}