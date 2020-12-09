import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arraysAmount = Integer.parseInt(scanner.nextLine());
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < arraysAmount; i++) {
            int[] readNumbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                                        .mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j < readNumbers.length; j++)
                numbers.add(readNumbers[j]);
        }
        int[] numbersArray = numbers.stream().mapToInt(Integer::valueOf).toArray();
        mergeSort(numbersArray, 0, numbersArray.length);
        for (int number : numbersArray)
            System.out.print(number + " ");
    }

    public static void mergeSort(int[] array, int leftIncl, int rightExcl) {
        if (rightExcl <= leftIncl + 1) {
            return;
        }
        int middle = leftIncl + (rightExcl - leftIncl) / 2;
        mergeSort(array, leftIncl, middle);  //sort left subarray
        mergeSort(array, middle, rightExcl); //sort right subarray
        merge(array, leftIncl, middle, rightExcl);
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int i = left;
        int j = middle;
        int k = 0;
        int[] temp = new int[right - left];
        while (i < middle && j < right) {
            if (array[i] >= array[j]) {
                temp[k] = array[i];
                i++;
            } else {
                temp[k] = array[j];
                j++;
            }
            k++;
        }
        for (; i < middle; i++, k++) {
            temp[k] = array[i];
        }
        for (; j < right; j++, k++) {
            temp[k] = array[j];
        }
        System.arraycopy(temp, 0, array, left, temp.length);
    }
}