import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static BigInteger factorial(BigInteger n) {
        return n.compareTo(BigInteger.ZERO) == 0 || n.compareTo(BigInteger.ONE) == 0
                ? BigInteger.ONE
                : n.multiply(factorial(n.subtract(BigInteger.ONE)));
    }

    public static void main(String[] args) {
        BigInteger m = new BigInteger(new Scanner(System.in).next());
        BigInteger n = BigInteger.ONE;
        while (factorial(n).compareTo(m) < 0) {
            n = n.add(BigInteger.ONE);
        }
        System.out.println(n);
    }
}