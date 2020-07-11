import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        switch (new Scanner(System.in).nextInt()) {
            case 1:
                System.out.println("Yes!");
                break;
            case 2:
            case 4:
            case 3:
                System.out.println("No!");
                break;
            default:
                System.out.println("Unknown number");
        }
    }
}