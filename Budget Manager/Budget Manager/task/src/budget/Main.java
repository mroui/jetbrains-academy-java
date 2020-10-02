package budget;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double sum = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            Matcher matcher = Pattern.compile("\\$(\\d+\\.\\d+|\\d+)").matcher(line);
            if (matcher.find()) {
                String value = matcher.group(0);
                sum += Double.parseDouble(value.substring(1));
            }
        }
        System.out.println("\nTotal: $" + sum);
    }
}
