import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int size = new Scanner(System.in).nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        for (int i : array) {
            System.out.print(getJumpSearchComparisons(array, i) + " ");
        }
    }

    public static int getJumpSearchComparisons(int[] array, int target) {
        int currentRight = 0;
        int prevRight = 0;
        int comparisons = 0;
        if (array.length == 0) {
            return -1;
        }
        if (array[currentRight] == target) {
            return 1;
        }
        int jumpLength = (int) Math.sqrt(array.length);
        while (currentRight < array.length - 1) {
            currentRight = Math.min(array.length - 1, currentRight + jumpLength);
            comparisons++;
            if (array[currentRight] >= target) {
                break;
            }
            prevRight = currentRight;
        }
        if ((currentRight == array.length - 1) && target > array[currentRight]) {
            comparisons++;
            return comparisons;
        }
        return getBackwardSearchComparisons(array, target, prevRight, currentRight, comparisons);
    }

    public static int getBackwardSearchComparisons(int[] array, int target, int leftExcl, int rightIncl, int comparisons) {
        for (int i = rightIncl; i > leftExcl; i--) {
            comparisons++;
            if (array[i] == target) {
                return comparisons;
            }
        }
        return comparisons;
    }
}