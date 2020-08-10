import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String part = scanner.nextLine();
        String line = scanner.nextLine();
        String regex = "(\\b+" + part + "[^\\s]+" + ")|(" + "\\s" + part + "\\s" + ")|(" + "[^\\s]+" + part + "\\b+)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        System.out.println(pattern.matcher(line).find() ? "YES" : "NO");
    }
}