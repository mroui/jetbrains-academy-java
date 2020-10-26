package budget;

import budget.item.Item;
import budget.item.ItemCategory;
import budget.item.ItemList;
import budget.menu.*;
import com.google.gson.annotations.Expose;

import java.util.*;

public class BudgetManager implements FileManager<BudgetManager> {

    private final Scanner scanner;
    private final Menu mainMenu;
    private final Menu addPurchaseMenu;
    private final Menu showPurchasesMenu;
    private final Menu sortMenu;
    private final Menu sortTypeMenu;
    @Expose
    private ItemList[] purchases;
    @Expose
    private double balance;

    public BudgetManager() {
        scanner = new Scanner(System.in);
        balance = 0.0d;
        mainMenu = new MainMenu(this::handleMainMenu);
        addPurchaseMenu = new PurchaseTypeMenu(this::handleAddPurchaseMenu);
        showPurchasesMenu = new ShowPurchasesMenu(this::handleShowPurchaseMenu);
        sortMenu = new SortMenu(this::handleSortMenu);
        sortTypeMenu = new PurchaseTypeMenu(this::handleSortTypeMenu);
        initPurchaseLists();
    }

    private void initPurchaseLists() {
        purchases = new ItemList[ItemCategory.values().length];
        for (int i = 0; i < purchases.length; i++)
            purchases[i] = new ItemList(ItemCategory.values()[i]);
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
                addPurchaseMenu.getListener().handleInput();
                break;
            case "3":
                showPurchasesMenu.getListener().handleInput();
                break;
            case "4":
                showBalance();
                break;
            case "5":
                if (write(this)) System.out.println("Purchases were saved!");
                break;
            case "6":
                handleLoadedData();
                break;
            case "7":
                sortMenu.getListener().handleInput();
                break;
            case "0":
                System.out.println("Bye!");
                return;
            default:
                System.out.println("Unknown operation.");
        }
        mainMenu.getListener().handleInput();
    }

    private void handleLoadedData() {
        BudgetManager loaded = read();
        if (loaded != null) {
            balance = loaded.balance;
            purchases = loaded.purchases;
            Arrays.stream(purchases).forEach(ItemList::recalculateSum);
            System.out.println("Purchases were loaded!");
        }
    }

    private void handleSortMenu() {
        sortMenu.show();
        String option = scanner.nextLine().trim();
        System.out.println();
        switch (option) {
            case "1":
                sortAllPurchases();
                break;
            case "2":
                sortByType();
                break;
            case "3":
                sortTypeMenu.getListener().handleInput();
                break;
            case "4":
                return;
            default:
                System.out.println("Unknown operation.");
        }
        sortMenu.getListener().handleInput();
    }

    private void sortAllPurchases() {
        if (isPurchaseNotEmpty()) {
            List<Item> allItems = new ArrayList<>();
            double sum = 0;
            for (ItemList list : purchases) {
                if (list.getList().size() > 0) {
                    sum += list.getSum();
                    allItems.addAll(list.getList());
                }
            }
            System.out.println("All:");
            Collections.sort(allItems);
            allItems.forEach(Item::print);
            System.out.println("Total sum: $" + sum);
        }
    }

    private void sortByType() {
        ItemList[] list = purchases.clone();
        Arrays.sort(list);
        System.out.println("Types:");
        double sum = 0;
        for (ItemList l : list) {
            System.out.println(l.getCategory() + " - $" + String.format("%.2f", l.getSum()));
            sum += l.getSum();
        }
        System.out.println("Total sum: $" + String.format("%.2f", sum));
    }

    private void handleSortTypeMenu() {
        sortTypeMenu.show();
        String option = scanner.nextLine().trim();
        System.out.println();
        ItemCategory category = ItemCategory.get(option);
        if (category != null)
            sortPurchase(category);
        else if (!option.equals("5")) {
            System.out.println("Unknown operation.");
            sortTypeMenu.getListener().handleInput();
        }
    }

    private void sortPurchase(ItemCategory category) {
        ItemList list = purchases[category.ordinal()];
        if (list.getList().size() > 0) {
            list.sort(false);
            list.print();
            list.printSum();
        } else System.out.println("Purchase list is empty!");
    }

    private void handleAddPurchaseMenu() {
        addPurchaseMenu.show();
        String option = scanner.nextLine().trim();
        System.out.println();
        ItemCategory category = ItemCategory.get(option);
        if (category != null)
            addPurchase(category);
        else if (option.equals("5"))
            return;
        else System.out.println("Unknown operation.");
        addPurchaseMenu.getListener().handleInput();
    }

    private void handleShowPurchaseMenu() {
        if (isPurchaseNotEmpty()) {
            showPurchasesMenu.show();
            String option = scanner.nextLine().trim();
            System.out.println();
            ItemCategory category = ItemCategory.get(option);
            if (category != null) {
                purchases[category.ordinal()].print();
                purchases[category.ordinal()].printSum();
            } else if (option.equals("5"))
                printAllPurchases();
            else if (option.equals("6"))
                return;
            else System.out.println("Unknown operation.");
            showPurchasesMenu.getListener().handleInput();
        }
    }

    private boolean isPurchaseNotEmpty() {
        for (ItemList list : purchases)
            if (list.getList().size() != 0)
                return true;
        System.out.println("Purchase list is empty!");
        return false;
    }

    private void printAllPurchases() {
        double sum = 0;
        for (ItemList list : purchases) {
            if (list.getList().size() > 0) {
                list.print();
                sum += list.getSum();
            }
        }
        System.out.println("Total sum: $" + sum);
    }

    private void addPurchase(ItemCategory category) {
        System.out.println("Enter purchase name:");
        String name = scanner.nextLine().replaceAll("\\s+", " ");
        System.out.println("Enter its price:");
        try {
            double value = parseAmount(scanner.nextLine());
            balance -= value;
            purchases[category.ordinal()].add(new Item(name, value));
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
