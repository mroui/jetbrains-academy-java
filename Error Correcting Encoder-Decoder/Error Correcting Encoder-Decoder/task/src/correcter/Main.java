package correcter;

public class Main {
    public static void main(String[] args) {
        showMenu();
    }

    private static void showMenu() {
        System.out.print("Write a mode: ");
        switch (new java.util.Scanner(System.in).next()) {
            case "encode":
                Operator.encode("send.txt", "encoded.txt");
                break;
            case "send":
                Operator.send("encoded.txt", "received.txt");
                break;
            case "decode":
                Operator.decode("received.txt", "decoded.txt");
                break;
            default:
                System.out.println("Bad operation!");
        }
    }
}
