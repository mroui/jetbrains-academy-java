package sorting;

public class Main {
    public static void main(final String[] args) {
        DataType dataType = args.length == 2 && args[0].equals("-dataType") ?
                DataType.valueOf(args[1].toUpperCase()) : DataType.WORD;
        new SortingTool(dataType).calculate();
    }
}