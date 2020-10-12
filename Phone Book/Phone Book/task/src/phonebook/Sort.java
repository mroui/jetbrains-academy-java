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
}
