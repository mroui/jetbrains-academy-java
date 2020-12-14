package sorting;

public class Arguments {

    private DataType dataType;
    private SortingType sortingType;
    private String inputFile;
    private String outputFile;

    private Arguments() {
        dataType = DataType.WORD;
        sortingType = SortingType.NATURAL;
    }

    public DataType dataType() {
        return dataType;
    }

    public SortingType sortingType() {
        return sortingType;
    }

    public String inputFile() {
        return inputFile;
    }

    public String outputFile() {
        return outputFile;
    }

    public static Arguments parse(String[] args) {
        Arguments arguments = new Arguments();
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i].toUpperCase()) {
                case "-DATATYPE":
                    if (i == args.length - 1 || args[i + 1].contains("-")) {
                        System.out.println("No data type defined!");
                        return null;
                    } else arguments.dataType = DataType.valueOf(args[i + 1].toUpperCase());
                    break;
                case "-SORTINGTYPE":
                    if (i == args.length - 1 || args[i + 1].contains("-")) {
                        System.out.println("No sorting type defined!");
                        return null;
                    } else arguments.sortingType = SortingType.valueOf(args[i + 1].toUpperCase());
                    break;
                case "-INPUTFILE":
                    if (i == args.length - 1 || args[i + 1].contains("-")) {
                        System.out.println("No input file defined!");
                        return null;
                    } else arguments.inputFile = args[i + 1];
                    break;
                case "-OUTPUTFILE":
                    if (i == args.length - 1 || args[i + 1].contains("-")) {
                        System.out.println("No output file defined!");
                        return null;
                    } else arguments.outputFile = args[i + 1];
                    break;
                default:
                    System.out.println("\"" + args[i] + "\"" + " isn't a valid parameter. It's skipped.");
                    --i;
                    break;
            }
        }
        return arguments;
    }
}
