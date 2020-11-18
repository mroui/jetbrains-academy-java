import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'y');
        String word = new Scanner(System.in).nextLine();
        int changesCounter = 0;
        int vowelsCounter = 0;
        int consonantsCounter = 0;
        for (int i = 0; i < word.length(); i++) {
            if (vowels.contains(word.toLowerCase().charAt(i))) {
                vowelsCounter++;
                consonantsCounter = 0;
            } else {
                consonantsCounter++;
                vowelsCounter = 0;
            }
            if (vowelsCounter == 3 || consonantsCounter == 3) {
                changesCounter++;
                vowelsCounter = 0;
                consonantsCounter = 0;
                i--;
            }
        }
        System.out.println(changesCounter);
    }
}