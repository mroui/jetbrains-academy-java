import java.util.Scanner;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String regex = scanner.nextLine();
        System.out.println(Pattern.compile(regex).matcher(text).results().count());
    }
}