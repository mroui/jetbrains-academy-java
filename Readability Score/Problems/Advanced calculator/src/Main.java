import java.util.ArrayList;
import java.util.List;

/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < args.length; i++)
            numbers.add(Integer.parseInt(args[i]));
        numbers.sort(Integer::compare);
        int result;
        switch (args[0]) {
            case "MAX":
                result = numbers.get(numbers.size() - 1);
                break;
            case "MIN":
                result = numbers.get(0);
                break;
            case "SUM":
                result = numbers.stream().reduce(0, Integer::sum);
                break;
            default:
                return;
        }
        System.out.println(result);
    }
}
