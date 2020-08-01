import java.util.function.*;


class Operator {

    public static LongBinaryOperator binaryOperator = (left, right) -> {
        long sum = 1;
        for (long i = left; i <= right; i++) {
            sum *= i;
        }
        return sum;
    };
}