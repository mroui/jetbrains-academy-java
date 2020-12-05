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
        String greatest = dataType == DataType.LINE ? calculateMaxLine() : calculateMaxLongOrWord();
        long times = data.stream().filter(s -> s.equals(greatest)).count();
        long percentage = times * 100 / data.size();

        System.out.println("Total numbers: " + data.size() + '.');
        System.out.print(dataType == DataType.LINE
                ? "The longest line:\n" + greatest + "\n("
                : "The greatest number: " + greatest + " (");
        System.out.println(times + " time(s), " +  percentage + "%).");
    }

    private String calculateMaxLine() {
        return data.stream().max(Comparator.comparingInt(String::length)).orElse("");
    }

    private String calculateMaxLongOrWord() {
        return data.stream().max((s, t) -> {
            try {
                return Long.valueOf(s).compareTo(Long.valueOf(t));
            } catch (NumberFormatException e) {
                return Integer.compare(s.length(), t.length());
            }
        }).orElse("");
    }
}
