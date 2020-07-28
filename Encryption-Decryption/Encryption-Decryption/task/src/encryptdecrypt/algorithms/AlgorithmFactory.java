package encryptdecrypt.algorithms;

import encryptdecrypt.algorithms.types.Algorithm;
import encryptdecrypt.algorithms.types.Reversed;
import encryptdecrypt.algorithms.types.Shift;
import encryptdecrypt.algorithms.types.Unicode;

public class AlgorithmFactory {

    public static Algorithm create(AlgorithmType type, String data, boolean mode, String input, String output, int key) {
        switch (type) {
            case REVERSED:
                return new Reversed(data, mode, input ,output);
            case SHIFT:
                return new Shift(data, mode, input, output, key);
            case UNICODE:
                return new Unicode(data, mode, input, output, key);
            default:
                return null;
        }
    }
}
