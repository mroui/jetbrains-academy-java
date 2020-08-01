import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;


class Operator {
    public static UnaryOperator<List<String>> unaryOperator = x -> List.copyOf(Set.copyOf(x));
}