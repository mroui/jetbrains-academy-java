import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {
        File file = new File("dataset_91065.txt");
        try {
            Scanner scanner = new Scanner(file);
            int amount = 0;
            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
				if (number == 0)
					break;
				if (number % 2 == 0)
					amount++;
			}
            System.out.println(amount);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}