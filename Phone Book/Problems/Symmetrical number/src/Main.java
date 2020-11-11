import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        String word = new Scanner(System.in).next();
        String part1 = word.substring(0, word.length() / 2);
        String part2 = new StringBuilder(word.substring(word.length() / 2)).reverse().toString();
        System.out.println(part2.equals(part1) ? 1 : 0);
    }
}