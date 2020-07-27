package encryptdecrypt.algorithms;

public class AlgorithmFactory {

    public static Algorithm create(AlgorithmType type, String data, String mode, String input, String output, int key) {
        switch (type) {
            case REVERSED:
                return new Reversed(data, mode, input ,output);
            case SHIFT:
                return new Shift(data, mode, input, output, key);
            default:
                return null;
        }
    }
}
