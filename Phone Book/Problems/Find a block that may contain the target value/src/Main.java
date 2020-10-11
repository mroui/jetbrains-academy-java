import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }
        int target = scanner.nextInt();
        System.out.println(jumpSearchBlock(numbers, target));
    }

    public static String jumpSearchBlock(int[] array, int searchEle) {
        int jumpLength = (int) Math.sqrt(array.length);
        int leftBlockIndex = 0;
        int rightBlockIndex = leftBlockIndex + jumpLength - 1;
        if (array.length == 0) {
            return "-1";
        }
        while (array[rightBlockIndex] < searchEle) {
            leftBlockIndex = rightBlockIndex + 1;
            rightBlockIndex = Math.min(array.length - 1, leftBlockIndex + jumpLength - 1);
            if (leftBlockIndex >= array.length) {
                return "-1";
            }
        }
        return leftBlockIndex + " " + rightBlockIndex;
    }
}