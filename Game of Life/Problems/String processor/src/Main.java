import java.util.Scanner;

class StringProcessor extends Thread {

    final Scanner scanner = new Scanner(System.in); // use it to read string from the standard input

    @Override
    public void run() {
        while (true) {
            String word = scanner.next();
            if (!word.equals(word.toUpperCase())) { //has lowercase
                System.out.println(word.toUpperCase());
            } else {
                System.out.println("FINISHED");
                break;
            }
        }
    }
}