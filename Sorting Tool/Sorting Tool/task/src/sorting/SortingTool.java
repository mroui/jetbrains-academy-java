package sorting;

import java.util.*;
import java.util.stream.Collectors;

public class SortingTool {

    private SortingType sortingType;
    private DataType dataType;
    private Map<Object, Integer> occurrencesData;

    private SortingTool() {
        occurrencesData = new TreeMap<>();
    }

    public SortingTool(DataType dataType, SortingType sortingType) {
        this();
        this.dataType = dataType;
        this.sortingType = sortingType;
        readData();
    }

    private void readData() {
        Scanner scanner = new Scanner(System.in);
        switch (dataType) {
            case WORD:
                while (scanner.hasNext()) addData(scanner.next());
                break;
            case LINE:
                while (scanner.hasNextLine()) addData(scanner.nextLine());
                break;
            case LONG:
                while (scanner.hasNextInt()) addData(scanner.nextInt());
        }
    }

    private void addData(Object value) {
        if (occurrencesData.containsKey(value))
            occurrencesData.put(value, occurrencesData.get(value) + 1);
        else occurrencesData.put(value, 1);
    }

    public void sort() {
        int sum = occurrencesData.values().stream().mapToInt(Integer::valueOf).sum();
        System.out.println("Total " + dataType.valuesName() + ": " + sum + '.');
        if (sortingType == SortingType.NATURAL)
            sortNaturally();
        else sortByCount();
    }

    private void sortNaturally() {
        System.out.print("Sorted data: ");
        if (dataType == DataType.LONG)
            sortNumbersNaturally();
        else sortWordsOrLinesNaturally();
        occurrencesData.forEach((k, v) -> {
            while (v-- > 0)
                System.out.print(k + " ");
        });
    }

    private void sortWordsOrLinesNaturally() {
        occurrencesData = occurrencesData.entrySet()
                                         .stream()
                                         .sorted(Comparator.comparing(e -> ((String) e.getKey())))
                                         .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                                 (oldValue, newValue) -> oldValue, HashMap::new));
    }

    private void sortNumbersNaturally() {
        occurrencesData = new TreeMap<>(occurrencesData);
    }

    private void sortByCount() {
        sortByOccurrences();
        int sum = occurrencesData.values().stream().mapToInt(Integer::valueOf).sum();
        occurrencesData.forEach((k, v) -> {
            int percentage = v * 100 / sum;
            System.out.println(k + ": " + v + " time(s), " + percentage + '%');
        });
    }

    private void sortByOccurrences() {
        occurrencesData = occurrencesData.entrySet().stream()
                                         .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                                         .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                                 (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
