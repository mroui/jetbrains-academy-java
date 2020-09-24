import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ticket = scanner.next();
        System.out.println(sum(ticket.substring(0, 3)) == sum(ticket.substring(3)) ? "Lucky" : "Regular");
    }

    public static int sum(String str) {
        int sum = 0;
        for (char ch : str.toCharArray()) {
            sum += Character.getNumericValue(ch);
        }
        return sum;
    }
}