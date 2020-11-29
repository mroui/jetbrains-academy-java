import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine().split("\\s")[0]);
        List<String> table = new ArrayList<>(rows);
        while (rows-- > 0)
            table.add(scanner.nextLine());
        Collections.rotate(table, scanner.nextInt());
        table.forEach(System.out::println);
    }
}