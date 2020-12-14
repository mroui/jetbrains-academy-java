package sorting;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static void main(final String[] args) {
        Arguments arguments = Arguments.parse(args);
        if (arguments != null) {
            SortingTool sortingTool = new SortingTool(arguments.dataType(), arguments.sortingType());
            try {
                if (arguments.inputFile() != null)
                    sortingTool.readData(new Scanner(Path.of(arguments.inputFile()), StandardCharsets.UTF_8.name()));
                else sortingTool.readData(new Scanner(System.in));
            } catch (IOException e) {
                System.out.println("Error while reading: " + e.toString());
            }
            sortingTool.sort();
            if (arguments.outputFile() != null) {
                try {
                    sortingTool.saveData(arguments.outputFile());
                } catch (IOException e) {
                    System.out.println("Error while saving: " + e.toString());
                }
            }
        }
    }


}