import java.io.File;
import java.util.Scanner;

class Main {

    private static String filename = "";
    private static int deepFile = 0;
    private static int tempDeep = 0;


    public static void main(String[] args) {
        System.out.print("Path of directory: ");
        findDeepestFile(new File(new Scanner(System.in).nextLine()));
        System.out.print(filename);
    }

    private static void findDeepestFile(File file) {
        try {
            for (File f : file.listFiles()) {
                if (f.isDirectory()) {
                    tempDeep++;
                    findDeepestFile(f);
                    if (tempDeep > deepFile) {
                        deepFile = tempDeep;
                        filename = f.getName();
                    }
                    tempDeep--;
                } else if (tempDeep > deepFile) {
                    deepFile = tempDeep;
                    filename = f.getName();
                }
            }
        } catch (NullPointerException ignored) {
        }
    }
}