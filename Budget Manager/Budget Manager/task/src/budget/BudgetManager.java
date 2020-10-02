package budget;

import budget.menu.MainMenu;
import budget.menu.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BudgetManager {

    private final Scanner scanner;
    private final Menu mainMenu;
    private double balance;
    private final List<Item> purchases;

    public BudgetManager() {
        scanner = new Scanner(System.in);
        balance = 0.0d;
        purchases = new ArrayList<>();
        mainMenu = new MainMenu(this::handleMainMenu);
    }

    public void run() {
        mainMenu.getListener().handleInput();
    }

    private void handleMainMenu() {
        mainMenu.show();
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
        mainMenu.getListener().handleInput();
    }

    private void showPurchases() {
        if (purchases.isEmpty())
            System.out.println("Purchase list is empty");
        else
            for (Item item : purchases)
                item.print();
    }

    private void addPurchase() {
        System.out.println("Enter purchase name:");
        String name = scanner.nextLine().replaceAll("\\s+", " ");
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
            String valueString = scanner.nextLine();
            if (!valueString.contains("-")) {
                double value = parseAmount(valueString);
                balance += value;
                System.out.println("Income was added!");
            } else System.out.println("Income cannot be negative!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private double parseAmount(String value) throws NullPointerException, NumberFormatException {
        return Double.parseDouble(value.trim().replace(',', '.').replaceAll("[-+]", ""));
    }

    private void showBalance() {
        System.out.println("Balance: $" + String.format("%.2f", balance));
    }

}
