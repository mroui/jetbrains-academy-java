import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("Problems/Count numbers in file/dataset_91033.txt"))) {
            String number = reader.readLine();
            int sum = Integer.parseInt(number);
            while ((number = reader.readLine()) != null)
                sum += Integer.parseInt(number);
            System.out.println(sum);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}