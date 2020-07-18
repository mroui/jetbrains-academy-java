import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        int[] b1 = {in.nextInt(), in.nextInt(), in.nextInt()};
        int[] b2 = {in.nextInt(), in.nextInt(), in.nextInt()};
        Arrays.sort(b1);
        Arrays.sort(b2);
        if (b1[0] == b2[0] && b1[1] == b2[1] && b1[2] == b2[2])
            System.out.println("Box 1 = Box 2");
        else if (b1[0] >= b2[0] && b1[1] >= b2[1] && b1[2] >= b2[2])
            System.out.println("Box 1 > Box 2");
        else if (b1[0] <= b2[0] && b1[1] <= b2[1] && b1[2] <= b2[2])
            System.out.println("Box 1 < Box 2");
        else
            System.out.println("Incomparable");
    }
}