package budget;

import java.util.Scanner;

public class BudgetManager {

    private final Scanner scanner = new Scanner(System.in);
    private Double balance;

    public BudgetManager() {
        balance = 0.0d;
    }

    public void run() {
        handleOperations();
    }

    private void handleOperations() {
        showMenu();
        String option = scanner.nextLine().trim();
        System.out.println();
        switch (option) {
            case "1":
                addIncome();
                break;
            case "2":
                //todo
                break;
            case "3":
                //todo
                break;
            case "4":
                showBalance();
                break;
            case "0":
                System.out.println("Bye!");
                return;
            default:
                System.out.println("Unknown operation.");
        }
        handleOperations();
    }

    private void addIncome() {
        System.out.println("Enter income:");
        try {
            double value = Double.parseDouble(scanner.nextLine().replace(',', '.'));
            if (value > 0) {
                balance += value;
                System.out.println("Income was added!");
            } else throw new Exception("Income wrong format!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void showBalance() {
        System.out.println("Balance: $" + balance);
    }

    private void showMenu() {
        System.out.println("\nChoose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "0) Exit");
    }

}
