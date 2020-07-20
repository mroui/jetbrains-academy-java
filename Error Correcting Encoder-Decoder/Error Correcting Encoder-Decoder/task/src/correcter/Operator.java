package correcter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static correcter.Converter.*;

public abstract class Operator {

    static void encode(String filenameInput, String filenameOutput) {
        try (OutputStream writer = new FileOutputStream(filenameOutput)) {
            String text = new String(Files.readAllBytes(Paths.get(filenameInput)));
            String hex = toHex(text, -1, 1);
            String bin = Converter.toBin(text, 8);

            String encryption = encodeHammingCode(bin);

            for (String str : getWithSpaces(encryption, 8).split(" "))
                writer.write((byte) Integer.parseInt(str, 2));

            System.out.println('\n' + filenameInput + ":\n"
                    + "text view: " + text + '\n'
                    + "hex view: " + getWithSpaces(hex, 2) + '\n'
                    + "bin view: " + getWithSpaces(bin, 8) + "\n\n"
                    + filenameOutput + ":\n"
                    + "expand: " + getWithSpaces(getExpandHammingFromParity(encryption), 8) + '\n'
                    + "parity: " + getWithSpaces(encryption, 8) + '\n'
                    + "hex view: " + getWithSpaces(toHex(encryption, 2, 8), 2));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void send(String filenameInput, String filenameOutput) {
        try (OutputStream writer = new FileOutputStream(filenameOutput)) {
            byte[] bytes = Files.readAllBytes(Paths.get(filenameInput));
            String bin = toBin(bytes);
            String hex = toHex(bin, 2, 8);

            String emulated = ErrorEmulator.emulateErrorOnBit(bin);

            for (String str : getWithSpaces(emulated, 8).split(" "))
                writer.write((byte) Integer.parseInt(str, 2));

            System.out.println('\n' + filenameInput + ":\n"
                    + "hex view: " + getWithSpaces(hex, 2) + '\n'
                    + "bin view: " + getWithSpaces(bin, 8) + "\n\n"
                    + filenameOutput + ":\n"
                    + "bin view: " + getWithSpaces(emulated, 8) + '\n'
                    + "hex view: " + getWithSpaces(toHex(emulated, 2, 8), 2));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void decode(String filenameInput, String filenameOutput) {
        try (OutputStream writer = new FileOutputStream(filenameOutput)) {
            byte[] bytes = Files.readAllBytes(Paths.get(filenameInput));
            String bin = toBin(bytes);
            String hex = toHex(bin, 2, 8);
            String decodedError = ErrorEmulator.decryptErrorOnBitParity(bin);
            String decodedParity = decodeParity(decodedError);
            String decodedRepetitions = removeRepetition(decodedParity, 2);

            int l = decodedRepetitions.length();
            String removedAdditional = decodedRepetitions;
            while (l % 8 != 0)
                removedAdditional = decodedRepetitions.substring(0, --l);

            StringBuilder decodedMessage = new StringBuilder();
            for (String str : getWithSpaces(removedAdditional, 8).split(" ")) {
                char ch = (char) Integer.parseInt(str, 2);
                decodedMessage.append(ch);
                writer.write((byte) ch);
            }

            System.out.println('\n' + filenameInput + ":\n"
                    + "hex view: " + getWithSpaces(hex, 2) + '\n'
                    + "bin view: " + getWithSpaces(bin, 8) + "\n\n"
                    + filenameOutput + ":\n"
                    + "correct: " + getWithSpaces(decodedError, 8) + '\n'
                    + "decode: " + getWithSpaces(decodedRepetitions, 8) + '\n'
                    + "remove: " + getWithSpaces(removedAdditional, 8) + '\n'
                    + "hex view: " + getWithSpaces(toHex(removedAdditional, 2, 8), 2) + '\n'
                    + "text view: " + decodedMessage);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static char binXOR(char x, char y, char z) {
        return Integer.toString((x ^ y) ^ Character.getNumericValue(z)).charAt(0);
    }

    private static String encodeHammingCode(String text) {
        StringBuilder encryption = new StringBuilder();
        for (int i = 0; i < text.length(); i += 4) {
            encryption.append(binXOR(text.charAt(i), text.charAt(i + 1), text.charAt(i + 3)));
            encryption.append(binXOR(text.charAt(i), text.charAt(i + 2), text.charAt(i + 3)));
            encryption.append(text.charAt(i));
            encryption.append(binXOR(text.charAt(i + 1), text.charAt(i + 2), text.charAt(i + 3)));
            encryption.append(text, i + 1, i + 4).append('0');
        }
        return encryption.toString();
    }

    public static String decodeParity(String text) {
        StringBuilder decryption = new StringBuilder();
        for (int i = 0; i < text.length(); i += 8)
            decryption.append(text, i, i + 6);
        return decryption.toString();
    }

    public static String encodeWithParity(String text) {
        StringBuilder textBuilder = new StringBuilder(text);
        while (textBuilder.length() % 6 != 0)
            textBuilder.append('0');
        StringBuilder encryption = new StringBuilder();
        text = textBuilder.toString();
        int parity = 0;
        for (int i = 0; i < text.length(); i += 2) {
            parity = parity ^ Integer.parseInt(text.substring(i, i + 1));
            encryption.append(text, i, i + 2);
            if ((i + 2) % 6 == 0) {
                parity %= 2;
                encryption.append(parity).append(parity);
                parity = 0;
            }
        }
        return encryption.toString();
    }

    private static String getExpandHammingFromParity(String binary) {
        StringBuilder result = new StringBuilder(binary);
        for (int i = 0; i < result.length(); i += 8) {
            result.setCharAt(i, '.');
            result.setCharAt(i + 1, '.');
            result.setCharAt(i + 3, '.');
            result.setCharAt(i + 7, '.');
        }
        return result.toString();
    }

    private static String getExpandFromParity(String binary) {
        StringBuilder result = new StringBuilder(binary);
        for (int i = 6; i < result.length(); i += 8) {
            result.setCharAt(i, '.');
            result.setCharAt(i + 1, '.');
        }
        return result.toString();
    }

}
