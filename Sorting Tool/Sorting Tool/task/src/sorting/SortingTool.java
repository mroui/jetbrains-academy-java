package sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SortingTool {

    private DataType dataType;
    private final List<String> data;

    public SortingTool() {
        data = new ArrayList<>();
    }

    public SortingTool(DataType type) {
        this();
        dataType = type;
        readData(type);
    }

    private void readData(DataType type) {
        Scanner scanner = new Scanner(System.in);
        if (type == DataType.LONG || type == DataType.WORD) {
            while (scanner.hasNext()) {
                data.add(scanner.next());
            }
        } else {    //DataType.LINE
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        }
    }

    public void calculate() {
        if (dataType == DataType.LONG || dataType == DataType.WORD) {
            calculateLongsOrWords();
        } else {    //DataType.LINE
            calculateLines();
        }
    }

    private void calculateLines() {
        String longestLine = data.stream().max(Comparator.comparingInt(String::length)).orElse("");
        long times = data.stream().filter(s -> s.equals(longestLine)).count();
        long percentage = times * 100 / data.size();
        System.out.println("Total numbers: " + data.size() + '.');
        System.out.println("The longest line:\n" + longestLine + "\n(" + times + " time(s), " +  percentage + "%).");
    }

    private void calculateLongsOrWords() {
        String greatest = data.stream().max((s, t) -> {
            try {
                return Long.valueOf(s).compareTo(Long.valueOf(t));
            } catch (NumberFormatException e) {
                return Integer.compare(s.length(), t.length());
            }
        }).orElse("");
        long times = data.stream().filter(s-> s.equals(greatest)).count();
        long percentage = times * 100 / data.size();
        System.out.println("Total numbers: " + data.size() + '.');
        System.out.println("The greatest number: " + greatest + " (" + times + " time(s), " +  percentage + "%).");
    }
}
