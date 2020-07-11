package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private Scanner waiter;
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;
    private boolean isOpen;

    public CoffeeMachine() {
        waiter = new Scanner(System.in);
        water = 400;
        milk = 540;
        coffeeBeans = 120;
        cups = 9;
        money = 550;
        isOpen = true;
    }

    public static void main(String[] args) {
        new CoffeeMachine().runCoffeeMachine();
    }

    private void runCoffeeMachine() {
        while (isOpen) {
            serve();
        }
    }

    private void serve() {
        System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
        switch (waiter.next()) {
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
            case "remaining":
                present();
                break;
            case "exit":
                isOpen = false;
                break;
            default:
                System.out.println("Excuse me, what do You want, sir?");
        }
    }

    private void take() {
        System.out.println("\nI gave you $" + money);
        money = 0;
    }

    private void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        water += waiter.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milk += waiter.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        coffeeBeans += waiter.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cups += waiter.nextInt();
    }

    private void buy() {
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        switch (waiter.next()) {
            case "1":
                tryToMakeCoffee(250, 0, 16, 4);
                break;
            case "2":
                tryToMakeCoffee(350, 75, 20, 7);
                break;
            case "3":
                tryToMakeCoffee(200, 100, 12, 6);
                break;
            case "back":
                break;
            default:
                System.out.println("Excuse me, we don't have it in stock, sir.");
        }
    }

    private void tryToMakeCoffee(int water, int milk, int coffeeBeans, int money) {
        boolean areResources = true;
        if (this.water < water) {
            System.out.println("Sorry, not enough water!");
            areResources = false;
        }
        if (this.milk < milk) {
            System.out.println("Sorry, not enough milk!");
            areResources = false;
        }
        if (this.coffeeBeans < coffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
            areResources = false;
        }
        if (this.cups == 0) {
            System.out.println("Sorry, not disposable cups!");
            areResources = false;
        }
        if (areResources) {
            System.out.println("I have enough resources, making you a coffee!");
            this.water -= water;
            this.milk -= milk;
            this.coffeeBeans -= coffeeBeans;
            this.money += money;
            cups--;
        }
    }

    private void present() {
        System.out.println("\nThe coffee machine has:\n" +
                water + " of water\n" +
                milk + " of milk\n" +
                coffeeBeans + " of coffee beans\n" +
                cups + " of disposable cups\n" +
                money + " of money\n");
    }
}