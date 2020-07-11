import java.util.*;

public class Main {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int size = sc.nextInt();
        int[][] tab = new int[size * size][size * size];
        for (int i = 0; i < size * size; i++) {
            for (int j = 0; j < size * size; j++) {
                tab[i][j] = sc.nextInt();
            }
        }
        System.out.print(checkSquares(size, tab) || checkLines(size, tab) ? "NO" : "YES");
    }

    public static boolean checkSquares(int size, int[][] tab) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < size * size; i += size) {
            for (int j = 0; j < size * size; j += size) {
                for (int k = 0; k < size; k++) {
                    for (int l = 0; l < size; l++) {
                        if (numbers.contains(tab[i + k][j + l]) || tab[i + k][j + l] > size * size) {
                            return true;
                        } else {
                            numbers.add(tab[i + k][j + l]);
                        }
                    }
                }
                numbers.clear();
            }
        }
        return false;
    }

    public static boolean checkLines(int size, int[][] tab) {
        ArrayList<Integer> numbersVertical = new ArrayList<>();
        ArrayList<Integer> numbersHorizontal = new ArrayList<>();
        for (int k = 0; k < size * size; k++) {
            for (int l = 0; l < size * size; l++) {
                if (numbersVertical.contains(tab[k][l])) {
                    return true;
                } else {
                    numbersVertical.add(tab[k][l]);
                }
                if (numbersHorizontal.contains(tab[l][k])) {
                    return true;
                } else {
                    numbersHorizontal.add(tab[l][k]);
                }
            }
            numbersVertical.clear();
            numbersHorizontal.clear();
        }
        return false;
    }
}
