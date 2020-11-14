import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        long number = new Scanner(System.in).nextLong();
        long smallestNumberFactorial = 0;
        int counter = 0;
        while (smallestNumberFactorial <= number) {
            counter++;
            smallestNumberFactorial = factorial(counter);
        }
        System.out.println(counter);
    }
    
    public static long factorial(long n) {
        return n <= 2 ? n : n * factorial(n - 1);
    }
}