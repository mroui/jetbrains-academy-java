package encryptdecrypt;

import java.io.*;
import java.util.InputMismatchException;

public class Main {

    private static String mode = "enc";
    private static String data = "";
    private static int key = 0;
    private static String input;
    private static String output;

    public static void main(String[] args) {
        try {
            parseArguments(args);
            execute();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getClass().getName());
        }
    }

    private static void execute() throws IOException {
        if (input != null && data.isEmpty())
            readFile();

        String result = mode.equals("enc") ? Shift.encrypt(data, key) : Shift.decrypt(data, key);

        if (output != null)
            writeFile(result);
        else
            System.out.println(result);
    }

    private static void writeFile(String result) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));
        writer.write(result);
        writer.close();
    }

    private static void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line = "";
        while ((line = reader.readLine()) != null)
            data += line;
        reader.close();
    }

    private static void parseArguments(String[] args) throws InputMismatchException {
        for (int i = 0; i < args.length; i += 2) {
            if (i + 1 < args.length)
                switch (args[i]) {
                    case "-mode":
                        mode = args[i + 1];
                        break;
                    case "-data":
                        data = args[i + 1];
                        break;
                    case "-key":
                        key = Integer.parseInt(args[i + 1]);
                        break;
                    case "-in":
                        input = args[i + 1];
                        break;
                    case "-out":
                        output = args[i + 1];
                        break;
                    default:
                        System.out.println("Unknown argument: " + args[i]);
                }
        }
    }

}
