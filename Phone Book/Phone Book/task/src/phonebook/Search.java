package phonebook;

import java.util.List;

public abstract class Search {

    public static long linear(List<Object> source, List<Object> toFind) {
        long found = 0;
        for (Object o1 : toFind)
            for (Object o2 : source)
                if (o1.equals(o2)) {
                    found++;
                    break;
                }
        return found;
    }
}
