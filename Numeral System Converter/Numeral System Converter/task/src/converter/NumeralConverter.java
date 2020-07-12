package converter;

public abstract class NumeralConverter {

    public static String decToBin(long number) {
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

    public static String decTo1(long number) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < number; i++)
            result.append(1);
        return result.toString();
    }

    public static String fractionalToDec(String number, int radix) {
        String[] numbers = number.substring(number.indexOf('.') + 1).split("");
        double result = 0;
        for (int i = 0; i < numbers.length; i++)
            result += Long.parseLong(numbers[i], radix) / Math.pow(radix, i + 1);
        return String.valueOf(result);
    }

    public static String fractionalDecToAny(double number, int radix) {
        StringBuilder result = new StringBuilder("0.");
        for (int i = 0; i < 5; i++) {
            double x = number * radix;
            result.append(Integer.toString((int) x, radix));
            number = x - (int) x;
        }
        return result.toString();
    }

    public static String fromToAnyRadix(int radixFrom, String number, int radixTo) {
        String integer = number.contains(".") ? number.substring(0, number.indexOf('.')) : number;
        String fractional = number.contains(".") ? "0" + number.substring(number.indexOf('.')) : "";

        long decimalInteger = radixFrom == 1 ? integer.length() : Long.parseLong(integer, radixFrom);
        String integerPart = radixTo == 1 ? decTo1(Long.parseLong(integer)) : Long.toString(decimalInteger, radixTo);

        String fractionalPart = "";
        if (!fractional.equals("")) {
            double decimalFractional = Double.parseDouble(fractionalToDec(fractional, radixFrom));
            fractionalPart = radixTo == 1 ? "" : fractionalDecToAny(decimalFractional, radixTo);
        }

        return radixTo == 1 || fractional.equals("") ? integerPart : integerPart + fractionalPart.substring(fractional.indexOf('.'));
    }

}
