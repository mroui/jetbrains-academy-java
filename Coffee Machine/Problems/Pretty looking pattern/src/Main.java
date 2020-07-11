import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] pattern = new char[4][4];
        for (int i = 0; i < 4; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < 4; j++) {
                pattern[i][j] = line.charAt(j);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pattern[i][j] == pattern[i][j + 1] && pattern[i][j] == pattern[i + 1][j]
                        && pattern[i][j] == pattern[i + 1][j + 1]) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }
}