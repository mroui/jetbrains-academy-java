package sorting;

public class Main {

    private static final String SORTING_TYPE = "-SORTINGTYPE";
    private static final String DATA_TYPE = "-DATATYPE";

    public static void main(final String[] args) {
        DataType dataType = DataType.WORD;
        SortingType sortingType = SortingType.NATURAL;
        try {
            if (args.length > 0) {
                for (int i = 0; i < args.length; i += 2) {
                    if (args[i].toUpperCase().equals(SORTING_TYPE))
                        sortingType = SortingType.valueOf(args[i + 1].toUpperCase());
                    else if (args[i].toUpperCase().equals(DATA_TYPE))
                        dataType = DataType.valueOf(args[i + 1].toUpperCase());
                }
            }
        } finally {
            new SortingTool(dataType, sortingType).sort();
        }
    }
}