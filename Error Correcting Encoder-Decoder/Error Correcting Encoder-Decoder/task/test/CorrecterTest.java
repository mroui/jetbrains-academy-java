import correcter.Main;

import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestClue {
    String input;

    TestClue(String input) {
        this.input = input;
    }
}

public class CorrecterTest extends StageTest<TestClue> {

    public CorrecterTest() throws Exception {
        super(Main.class);
    }

    @Override
    public List<TestCase<TestClue>> generate() {
        TestClue[] testClues = new TestClue[]{
            new TestClue("Some text to test"),
            new TestClue("send message to user with id #42354"),
            new TestClue("thq")
        };

        List<TestCase<TestClue>> result = new ArrayList<>();

        for (int i = 0; i < testClues.length; i++) {
            result.add(new TestCase<TestClue>()
                .setAttach(testClues[i])
                .setInput(testClues[i].input));
        }

        return result;
    }

    @Override
    public CheckResult check(String reply, TestClue clue) {
        List<String> splitReply = Arrays.asList(reply.strip().split("\\n"));

        if (splitReply.size() != 4) {
            return new CheckResult(false,
                "Your program should output 4 lines, found: " + splitReply.size());
        }

        String initialLine = splitReply.get(0);
        String stretched = splitReply.get(1);
        String received = splitReply.get(2);
        String decoded = splitReply.get(3);


        if (!initialLine.equals(clue.input)) {
            return new CheckResult(false,
                "First line of output should be an input reference!");
        }

        if (!stretched.equals(stretchString(initialLine))) {
            return new CheckResult(false,
                "Text before sending is encoded incorrectly!");
        }

        if (!decoded.equals(initialLine)) {
            return new CheckResult(false,
                "Decoding result not match required! Make sure the program works correctly!");
        }

        return checkMatches(received, stretched);
    }

    private static String stretchString(String target) {
        char[] resultChars = target.toCharArray();
        char[] result = new char[resultChars.length * 3];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultChars[i / 3];
        }

        return new String(result);
    }

    private CheckResult checkMatches(String userOutput,
                                     String correctOutput) {

        if (userOutput.length() != correctOutput.length()) {
            return new CheckResult(false,
                "Input length and output length should be the same!\n" +
                    "Input length: " + correctOutput.length() +
                    "Output length: " + userOutput.length());
        }

        for (int i = 0; i < userOutput.length(); i+=3) {

            int from = i;
            int to = Math.min(i+3, userOutput.length());

            String currUserPart = userOutput.substring(from, to);
            String currCorrectPart = correctOutput.substring(from, to);

            if (currUserPart.length() != 3) {
                break;
            }

            int errors = 0;

            for (int j = 0; j < currUserPart.length(); j++) {
                if (currUserPart.charAt(j) != currCorrectPart.charAt(j)) {
                    errors++;
                }
            }

            if (errors != 1) {
                return new CheckResult(false,
                    "One of the triples contain "
                        + errors + " errors, but every triple should always contain 1 error");
            }
        }

        return CheckResult.correct();
    }
}
