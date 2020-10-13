package phonebook;

import java.util.List;

public class Search<T extends Comparable<T>> {

    public long linear(List<T> source, List<T> toFind) {
        long found = 0;
        for (Object o1 : toFind)
            for (Object o2 : source)
                if (o1.equals(o2)) {
                    found++;
                    break;
                }
        return found;
    }

    public long jump(List<T> source, List<T> toFind) {
        long found = 0;
        int jumpLength = (int) Math.sqrt(source.size());
        for (T target : toFind) {
            int currentRight = 0;
            int prevRight = 0;
            while (currentRight < source.size() - 1) {
                currentRight = Math.min(source.size() - 1, currentRight + jumpLength);
                if (source.get(currentRight).compareTo(target) >= 0)
                    break;
                prevRight = currentRight;
            }
            if ((currentRight != source.size() - 1) || source.get(currentRight).compareTo(target) >= 0)
                found += backward(source, target, prevRight, currentRight) != -1 ? 1 : 0;
        }
        return found;
    }

    public long backward(List<T> source, T target, int leftExcl, int rightIncl) {
        for (int i = rightIncl; i > leftExcl; i--)
            if (source.get(i).equals(target))
                return i;
        return -1;
    }

    public long binary(List<T> source, List<T> toFind) {
        long found = 0;
        for (T target : toFind) {
            int left = 0, right = source.size() - 1;
            while (left <= right) {
                int mid = (right + left) / 2;
                if (source.get(mid).equals(target)) {
                    found++;
                    break;
                }
                if (source.get(mid).compareTo(target) < 0)
                    left = mid + 1;
                else right = mid - 1;
            }
        }
        return found;
    }
}
