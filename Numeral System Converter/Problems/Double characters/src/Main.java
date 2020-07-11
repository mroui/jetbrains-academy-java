import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Arrays.stream(new Scanner(System.in).nextLine().split(""))
                .map(it -> it + it)
                .forEach(System.out::print);
    }
}