import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {
        File file = new File("dataset_91022.txt");
        try {
            Scanner scanner = new Scanner(file);
            int amount = 0;
            while (scanner.hasNextInt())
                if (scanner.nextInt() >= 9999)
                    amount++;
            System.out.println(amount);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}