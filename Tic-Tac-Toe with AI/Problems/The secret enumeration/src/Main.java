public class Main {

    public static void main(String[] args) {
        System.out.println(countEnumsStartWith(Secret.values(), "STAR"));
    }

    public static int countEnumsStartWith(Enum[] enums, String prefix) {
        int counter = 0;
        for (Enum e : enums) {
            if (e.name().startsWith(prefix)) {
                counter++;
            }
        }
        return counter;
    }
}