package phonebook;

import java.util.List;

public class Sort<T extends Comparable<T>> {

    public void bubble(List<T> source) {
        for (int i = 0; i < source.size() - 1; i++)
            for (int j = 0; j < source.size() - i - 1; j++)
                if (source.get(j).compareTo(source.get(j + 1)) > 0) {
                    T temp = source.get(j);
                    source.set(j, source.get(j + 1));
                    source.set(j + 1, temp);
                }
    }

    public void quick(List<T> source, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(source, left, right);
            quick(source, left, pivotIndex - 1);
            quick(source, pivotIndex + 1, right);
        }
    }
    private int partition(List<T> source, int left, int right) {
        T pivot = source.get(right);
        int partitionIndex = left;
        for (int i = left; i < right; i++) {
            if (source.get(i).compareTo(pivot) <= 0) {
                swap(source, i, partitionIndex);
                partitionIndex++;
            }
        }
        swap(source, partitionIndex, right);
        return partitionIndex;
    }

    private void swap(List<T> source, int prev, int next) {
        T temp = source.get(prev);
        source.set(prev, source.get(next));
        source.set(next, temp);
    }
}
