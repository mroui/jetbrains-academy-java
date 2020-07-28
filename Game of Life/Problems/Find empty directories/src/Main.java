import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<String> emptyDirs = new ArrayList<>();

    public static void main(String[] args) {
        System.out.print("Path of directory: ");
        getEmptyDirs(new File(new Scanner(System.in).nextLine()));
        for (String f : emptyDirs)
            System.out.print(f + " ");
    }

    private static void getEmptyDirs(File file) {
        try {
            for (File f : file.listFiles())
                if (f.isDirectory())
                    if (f.listFiles().length == 0)
                        emptyDirs.add(f.getName());
                    else getEmptyDirs(f);
        } catch (NullPointerException ignored) {
        }
    }
}