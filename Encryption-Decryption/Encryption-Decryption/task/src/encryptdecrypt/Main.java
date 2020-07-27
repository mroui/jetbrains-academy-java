package encryptdecrypt;

import encryptdecrypt.algorithms.Algorithm;
import encryptdecrypt.algorithms.AlgorithmFactory;
import encryptdecrypt.algorithms.AlgorithmType;

import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0)
            System.out.println("\nWrong arguments!\n\n" +
                    "[-mode]\t[enc | dec] - Encryption / decryption mode\n\n" +
                    "[-data]\t[message] - string message to encrypt / decrypt\n\n" +
                    "[-in]\t[filename.txt] - Read data from file to encrypt / decrypt\n\n" +
                    "[-out]\t[filename.txt] - Write result data to file\n\n" +
                    "[-key]\t[1..n] - Integer shift key\n");
        else run(args);
    }

    private static void run(String[] args) {
        try {
            Algorithm algorithm = parseArguments(args);
            algorithm.execute();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getClass().getName());
        }
    }

    private static Algorithm parseArguments(String[] args) throws InputMismatchException {
        String mode = "enc";
        String data = "";
        int key = 0;
        String input = null;
        String output = null;
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
        return AlgorithmFactory.create(AlgorithmType.SHIFT, data, mode, input, output, key);
    }
}
