import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        String[] strings = new Scanner(System.in).nextLine().split(" ");
        for (int i = 1; i < strings.length; i++) {
            if (strings[i].toLowerCase().compareTo(strings[i - 1]) < 0) {
                System.out.println("false");
                return;
            }
        }
        System.out.println("true");
    }
}