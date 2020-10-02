import budget.Main;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.TestCase;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BudgetManagerTest extends StageTest<String> {

    public BudgetManagerTest() {
        super(Main.class);
    }

    @Override
    public List<TestCase<String>> generate() {
        return List.of(

                new TestCase<String>()
                        .setInput("0\n")
                        .setCheckFunc(BudgetManagerTest::test1),

                new TestCase<String>()
                        .setInput("0\n")
                        .setCheckFunc(BudgetManagerTest::test2),

                new TestCase<String>()
                        .setInput("4\n0")
                        .setCheckFunc(BudgetManagerTest::test3),

                new TestCase<String>()
                        .setInput("1\n400\n4\n1\n200\n4\n0")
                        .setCheckFunc(BudgetManagerTest::test4),

                new TestCase<String>()
                        .setInput("3\n1\n600\n2\nRed Fuji Apple\n5.99\n2\nEggs\n3.99\n3\n4\n0")
                        .setCheckFunc(BudgetManagerTest::test5)
        );
    }


    //Checking program stop
    private static CheckResult test1(String reply, String attach) {
        if (!reply.contains("Bye!")) {
            return new CheckResult(false,
                    "Your program should stop after choosing \"Exit\"");
        }
        return new CheckResult(true);
    }


    //Checking menu
    private static CheckResult test2(String reply, String attach) {
        String[] menuPatterns = {"1)", "2)", "3)", "4)", "0)"};
        for (String menuPattern : menuPatterns) {
            if (!reply.contains(menuPattern)) {
                return new CheckResult(false,
                        "Your menu doesn't have item " + menuPattern);
            }
        }
        return new CheckResult(true);
    }


    //Checking balance
    private static CheckResult test3(String reply, String attach) {

        String[] blocks = reply.split("\n(\n+)?\n");

        if (blocks.length != 4) {
            return new CheckResult(false,
                    "Your program shows wrong blocks of output. Expected: 4\n" +
                            "You have: " + blocks.length + "\n" +
                            "Make sure that you print an empty line after each chosen action");
        }

        String balance = blocks[1];

        if (!balance.toLowerCase().contains("balance")) {
            return new CheckResult(false,
                    "Your program should show balance after choosing 4th item");
        }

        Pattern doublePattern = Pattern.compile("\\d+[,.]\\d+");
        Matcher matcher = doublePattern.matcher(balance);

        if (!matcher.find()) {
            return new CheckResult(false,
                    "Your balance should contain a number!");
        }

        double balanceDouble = Double.parseDouble(matcher.group());

        if (Math.abs(balanceDouble - 0) > 0.0001) {
            System.out.println(balance);
            return new CheckResult(false,
                    "Balance should be $0.00 at the beginning");
        }

        return new CheckResult(true);
    }


    //Checking adding income
    private static CheckResult test4(String reply, String attach) {

        String[] blocks = reply.split("\n(\n+)?\n");

        if (blocks.length != 10) {
            return new CheckResult(false,
                    "Your program shows wrong blocks of output. Expected: 10\n" +
                            "You have: " + blocks.length + "\n" +
                            "Make sure that you print an empty line after each chosen action");
        }

        String balanceAfterFirstAddingIncome = blocks[3];

        if (!balanceAfterFirstAddingIncome.contains("$400")) {
            return new CheckResult(false,
                    "Balance is wrong after adding income!.\n" +
                            "Expected:\n" +
                            "Balance: $400.00\n" +
                            "Your output:\n" + balanceAfterFirstAddingIncome);
        }

        return new CheckResult(true);
    }


    //Checking adding purchase
    private static CheckResult test5(String reply, String attach) {

        String[] blocks = reply.split("\n(\n+)?\n");

        if (blocks.length != 14) {
            return new CheckResult(false,
                    "Your program shows wrong blocks of output. Expected: 14\n" +
                            "You have: " + blocks.length + "\n" +
                            "Make sure that you print an empty line after each chosen action");
        }

        String emptyPurchaseList = blocks[1];

        if (!emptyPurchaseList.contains("empty")) {
            return new CheckResult(false,
                    "Purchase list should be empty at the beginning!");
        }

        String purchaseList = blocks[9];

        String[][] purchases = {
                {"Red Fuji Apple", "5.99"},
                {"Eggs", "3.99"}
        };

        for (String[] purchase : purchases) {
            if (!purchaseList.contains(purchase[0])) {
                return new CheckResult(false,
                        "Your purchase list doesn't contain \"" + purchase[0] + "\"");
            }
            if (!purchaseList.contains(purchase[1])) {
                return new CheckResult(false,
                        "Your purchase list doesn't have price of " + purchase[0]);
            }
        }

        String balanceAfterAddingPurchases = blocks[11];

        Pattern doublePattern = Pattern.compile("\\d+[,.]\\d+");
        Matcher matcher = doublePattern.matcher(balanceAfterAddingPurchases);

        if (!matcher.find()) {
            return new CheckResult(false,
                    "Your balance should contain a number!");
        }

        double balance = Double.parseDouble(matcher.group());

        if (Math.abs(balance - 590.02) > 0.0001) {
            return new CheckResult(false,
                    "Your balance should change after adding purchase.\n" +
                            "Expected: Balance $590.02\n" +
                            "Your output: " + balanceAfterAddingPurchases);
        }

        return new CheckResult(true);
    }


}
