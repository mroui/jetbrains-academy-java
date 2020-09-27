import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        int shift = scanner.nextInt();
        System.out.println(shift < text.length() ? text.substring(shift) + text.substring(0, shift) : text);
    }
}