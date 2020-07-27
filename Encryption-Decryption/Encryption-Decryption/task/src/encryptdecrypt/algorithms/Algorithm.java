package encryptdecrypt.algorithms;

import java.io.*;

public abstract class Algorithm {

    String message;
    String mode;
    String input;
    String output;

    Algorithm(String message, String mode, String input, String output) {
        this.message = message;
        this.mode = mode;
        this.input = input;
        this.output = output;
    }

    abstract String encrypt();

    abstract String decrypt();

    public void writeFile(String result) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(output));
        writer.write(result);
        writer.close();
    }

    public void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line = "";
        while ((line = reader.readLine()) != null)
            message += line;
        reader.close();
    }

    public void execute() throws IOException {
        if (input != null && message.isEmpty())
            readFile();
        String result = mode.equals("enc") ? encrypt() : decrypt();
        if (output != null)
            writeFile(result);
        else
            System.out.println(result);
    }
}
