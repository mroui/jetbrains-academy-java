package sorting;

public class Main {

    private static final String SORTING_TYPE = "-SORTINGTYPE";
    private static final String DATA_TYPE = "-DATATYPE";

    public static void main(final String[] args) {
        DataType dataType = DataType.WORD;
        SortingType sortingType = SortingType.NATURAL;
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].toUpperCase().equals(DATA_TYPE))
                if (args.length == 1 || i == args.length - 1 || args[i + 1].contains("-")) {
                    System.out.println("No data type defined!");
                    return;
                } else dataType = DataType.valueOf(args[i + 1].toUpperCase());
            else if (args[i].toUpperCase().equals(SORTING_TYPE))
                if (args.length == 1 || i == args.length - 1 || args[i + 1].contains("-")) {
                    System.out.println("No sorting type defined!");
                    return;
                } else sortingType = SortingType.valueOf(args[i + 1].toUpperCase());
            else {
                System.out.println("\"" + args[i] + "\"" + " isn't a valid parameter. It's skipped.");
                --i;
            }
        }
        new SortingTool(dataType, sortingType).sort();
    }
}