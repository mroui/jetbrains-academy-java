package converter;

public abstract class NumeralConverter {

    public static String decToBin(int number) {
        StringBuilder binary = new StringBuilder();
        if (number == 0)
            binary.append(0);
        while (number > 0) {
            binary.append(number % 2);
            number /= 2;
        }
        binary = binary.reverse();
        return "0b" + binary;
    }

    public static String decToRadix(long number, int radix) {
        return (radix == 2 ? "0b" : radix == 8 ? "0" : radix == 16 ? "0x" : "") + Long.toString(number, radix);
    }

    private static String decTo1(long number) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < number; i++)
            result.append(1);
        return result.toString();
    }

    public static String fromToAnyRadix(int radixFrom, String number, int radixTo) {
        long decimal = radixFrom == 1 ? number.length() : Long.parseLong(number, radixFrom);
        return radixTo == 1 ? decTo1(Long.parseLong(number)) : Long.toString(decimal, radixTo);
    }

}
