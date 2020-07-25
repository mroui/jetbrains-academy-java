import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String longest = "";
        for (String s : text.split("\\s")) {
            if (s.length() > longest.length()) {
                longest = s;
            }
        }
        System.out.println(longest);
    }
}