package sorting;

public class Main {

    public static void main(final String[] args) {
        Arguments arguments = Arguments.parse(args);
        if (arguments != null) {
            new SortingTool(arguments.dataType(), arguments.sortingType()).sort();
        }
    }


}