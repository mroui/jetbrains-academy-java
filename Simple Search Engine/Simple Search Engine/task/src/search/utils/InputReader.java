package search.utils;

import java.util.Scanner;

public class InputReader {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readLine() {
        return scanner.nextLine();
    }

    public static int readInt() {
        return Integer.parseInt(readLine());
    }
}
