package budget;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BudgetManager {

    private final Scanner scanner = new Scanner(System.in);
    private double balance;
    private final List<Item> purchases;

    public BudgetManager() {
        balance = 0.0d;
        purchases = new ArrayList<>();
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
                addPurchase();
                break;
            case "3":
                showPurchases();
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

    private void showPurchases() {
        if (purchases.isEmpty()) {
            System.out.println("Purchase list is empty");
        } else {
            for (Item item : purchases) {
                item.print();
            }
        }
    }

    private void addPurchase() {
        System.out.println("Enter purchase name:");
        String name = scanner.nextLine().trim();
        System.out.println("Enter its price:");
        try {
            double value = parseAmount(scanner.nextLine());
            balance -= value;
            purchases.add(new Item(name, value));
            System.out.println("Purchase was added!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void addIncome() {
        System.out.println("Enter income:");
        try {
            double value = parseAmount(scanner.nextLine());
            if (value > 0) {
                balance += value;
                System.out.println("Income was added!");
            } else throw new Exception("Income wrong format!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private double parseAmount(String value) {
        return Double.parseDouble(value.trim().replace(',', '.').replaceAll("[-+]", ""));
    }

    private void showBalance() {
        System.out.println("Balance: $" + String.format("%.2f", balance));
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
