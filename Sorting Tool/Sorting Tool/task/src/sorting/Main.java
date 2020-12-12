package sorting;

import java.util.Arrays;

public class Main {
    public static void main(final String[] args) {
        ArgumentsType argumentsType = ArgumentsType.WORD;
        if (args.length > 0) {
            if (Arrays.asList(args).contains("-sortIntegers"))
                argumentsType = ArgumentsType.SORT_INTEGERS;
            else if (args[0].equals("-dataType") && args.length > 1)
                argumentsType = ArgumentsType.valueOf(args[1].toUpperCase());
        }
        new SortingTool(argumentsType).start();
    }
}