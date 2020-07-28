import java.io.File;
import java.util.Scanner;

public class Main {

    private static int max = 0;
    private static String filename = "";

    public static void main(String[] args) {
        System.out.print("Path of directory: ");
        findBiggestDir(new File(new Scanner(System.in).nextLine()));
        System.out.print(filename + " " + max);
    }

    private static void findBiggestDir(File file) {
        try {
            for (File f : file.listFiles())
                if (f.isDirectory())
                    if (f.listFiles().length > max) {
                        max = f.listFiles().length;
                        filename = f.getName();
                    } else findBiggestDir(f);
        } catch (NullPointerException ignored) {
        }
    }
}