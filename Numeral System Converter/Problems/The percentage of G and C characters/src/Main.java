import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        String str = new Scanner(System.in).next();
        double result = (double) str.chars()
                .filter(ch -> ch == 'g' || ch == 'G' || ch == 'c' || ch == 'C')
                .count() * 100 / str.length();
        System.out.println(result);
    }
}
